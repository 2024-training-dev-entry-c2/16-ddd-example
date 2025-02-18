package com.buildingblocks.industries.domain.industry;

import com.buildingblocks.industries.domain.industry.events.*;
import com.buildingblocks.industries.domain.industry.values.*;
import com.buildingblocks.shared.domain.generic.DomainActionsContainer;
import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.List;
import java.util.function.Consumer;

public class IndustryHandler extends DomainActionsContainer {

    public IndustryHandler(Industry industry) {
        add(buildIndustry(industry));
        add(activateMarketLink(industry));
        add(consumeResource(industry));
        add(isExhausted(industry));
        add(flip(industry));
        add(overBuild(industry));
        add(upgrade(industry));
    }

    public Consumer<? extends DomainEvent> buildIndustry(Industry industry) {
        return (BuiltIndustry event) -> {
            industry.setType(Type.of(event.getType()));
            industry.setLevel(Level.of(event.getLevel()));
            industry.setLocation(Location.of(event.getLocation()));
            industry.setCost(Cost.of(event.getCost()));
            industry.setRequiredResource(RequiredResource.of(event.getRequiredResource()));
            industry.setTechLevelRequired(TechLevelRequired.of(event.getTechLevelRequired()));
            industry.setIsConnectedToNetwork(IsConnectedToNetwork.of(event.getIsConnectedToNetwork()));
            industry.setEra(Era.of(event.getEra()));
            industry.setIsFlipped(IsFlipped.of(event.getFlipped()));
        };
    }

    public Consumer<? extends DomainEvent> activateMarketLink(Industry industry) {
        return (ActivatedIndustryMarketLink event) -> {
            industry.setIsConnectedToNetwork(IsConnectedToNetwork.of(event.getConnectedToNetwork()));
        };
    }

    public Consumer<? extends DomainEvent> consumeResource(Industry industry) {
        return (ConsumedResource event) -> {
            String requiredResource = event.getRequiredResource();
            int requiredQuantity = event.getQuantityRequiredResource();

            List<String> storedResources = industry.getStoredResources().getValue();

            long availableQuantity = storedResources.stream().filter(r -> r.equals(requiredResource)).count();

            if (availableQuantity >= requiredQuantity) {
                for (int i = 0; i < requiredQuantity; i++) {
                    storedResources.remove(requiredResource);
                }
                industry.setStoredResources(StoredResources.of(storedResources));
            } else {
                throw new IllegalStateException("Not enough resources to consume");
            }
        };
    }


    public Consumer<? extends DomainEvent> isExhausted(Industry industry) {
        return (ExhaustedIndustry event) -> {
            industry.setIsFlipped(IsFlipped.of(event.getFlipped()));
        };
    }

    public Consumer<? extends DomainEvent> flip(Industry industry) {
        return (FlippedIndustry event) -> {
            industry.setIsFlipped(IsFlipped.of(true));
            industry.setIncome(Income.of(event.getIncome()));
        };
    }

    public Consumer<? extends DomainEvent> overBuild(Industry industry) {
        return (OverBuiltIndustry event) -> {
            industry.setType(Type.of(event.getType()));
            industry.setLevel(Level.of(event.getLevel()));
            industry.setCost(Cost.of(event.getCost()));
        };
    }

    public Consumer<? extends DomainEvent> upgrade(Industry industry) {
        return (UpgradedIndustry event) -> {
            industry.setType(Type.of(event.getType()));
            industry.setLevel(Level.of(event.getLevel()));
            industry.setLocation(Location.of(event.getLocation()));
            industry.setIsFlipped(IsFlipped.of(event.getFlipped()));
            industry.setRequiredResource(RequiredResource.of(event.getRequiredResource()));
            industry.setQuantityRequiredResource(QuantityRequiredResource.of(event.getQuantityRequiredResource()));
            industry.setCost(Cost.of(event.getCost()));
            industry.setTechLevelRequired(TechLevelRequired.of(event.getTechLevelRequired()));
            industry.setIsRequiredResearch(IsRequiredResearch.of(event.getIsRequiredResearch()));
            industry.setEra(Era.of(event.getEra()));
        };
    }
}
