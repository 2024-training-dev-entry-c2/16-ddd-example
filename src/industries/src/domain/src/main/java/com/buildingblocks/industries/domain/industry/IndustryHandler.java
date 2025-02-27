package com.buildingblocks.industries.domain.industry;

import com.buildingblocks.industries.domain.industry.events.*;
import com.buildingblocks.industries.domain.industry.values.*;
import com.buildingblocks.shared.domain.generic.DomainActionsContainer;
import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class IndustryHandler extends DomainActionsContainer {

    public IndustryHandler(Industry industry) {
        add(build(industry));
        add(activateMarketLink(industry));
        add(consumeResource(industry));
        add(isExhausted(industry));
        add(flip(industry));
        add(overBuild(industry));
        add(upgrade(industry));
    }

    public Consumer<? extends DomainEvent> build(Industry industry) {
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

            List<String> storedResources = new ArrayList<>(industry.getStoredResources().getValue());
            long availableQuantity = storedResources.stream().filter(r -> r.equals(requiredResource)).count();

            if (availableQuantity < requiredQuantity)
                throw new IllegalStateException("Not enough resources to consume");

            List<String> updatedResources = new ArrayList<>();
            int removed = 0;

            for (String resource : storedResources) {
                if (resource.equals(requiredResource) && removed < requiredQuantity) removed++;
                else updatedResources.add(resource);
            }
            industry.setStoredResources(StoredResources.of(updatedResources));
        };
    }

    public Consumer<? extends DomainEvent> isExhausted(Industry industry) {
        return (ExhaustedIndustry event) -> {
            industry.setIsFlipped(IsFlipped.of(true));
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
            industry.setIsFlipped(IsFlipped.of(true));
            industry.setRequiredResource(RequiredResource.of(event.getRequiredResource()));
            industry.setQuantityRequiredResource(QuantityRequiredResource.of(event.getQuantityRequiredResource()));
            industry.setCost(Cost.of(event.getCost()));
            industry.setTechLevelRequired(TechLevelRequired.of(event.getTechLevelRequired()));
            industry.setIsRequiredResearch(IsRequiredResearch.of(false));
            industry.setEra(Era.of(event.getEra()));
        };
    }
}
