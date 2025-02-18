package com.buildingblocks.industries.domain.resourceMarket;

import com.buildingblocks.industries.domain.resourceMarket.events.DepletedMarketSupply;
import com.buildingblocks.industries.domain.resourceMarket.events.ExecutedTrade;
import com.buildingblocks.industries.domain.resourceMarket.events.RefilledMarketSupply;
import com.buildingblocks.industries.domain.resourceMarket.events.UpdatedResourcePrice;
import com.buildingblocks.industries.domain.resourceMarket.values.*;
import com.buildingblocks.shared.domain.generic.DomainActionsContainer;
import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ResourceMarketHandler extends DomainActionsContainer {

    public ResourceMarketHandler(ResourceMarket resourceMarket) {
        add(depleteMarketSupply(resourceMarket));
        add(executeTrade(resourceMarket));
        add(refillMarketSupply(resourceMarket));
        add(updatePrice(resourceMarket));
    }

    public Consumer<? extends DomainEvent> depleteMarketSupply(ResourceMarket resourceMarket) {
        return (DepletedMarketSupply event) -> {
            List<String> updatedResourcesList = new ArrayList<>();
            updatedResourcesList.add(String.valueOf(event.getUpdatedAvailableResources()));

            AvailableResources updatedResources = AvailableResources.of(updatedResourcesList);
            resourceMarket.setAvailableResources(updatedResources);

            System.out.println("Market supply depleted for resource: " + event.getResourceType() +
                    ". Updated available resources: " + updatedResourcesList);
        };
    }

    public Consumer<? extends DomainEvent> executeTrade(ResourceMarket resourceMarket) {
        return (ExecutedTrade event) -> {
            String tradeType = event.getTradeType();
            String resourceType = event.getResourceType();
            String tradeId = event.getId();

            Integer totalResourcesPrice = event.getTotalResourcesPrice();
            Integer resourceQuantity = event.getResourceQuantity();

            resourceMarket.executeTrade(tradeId, tradeType, resourceType, totalResourcesPrice, resourceQuantity);

            System.out.println("Executed trade: " + tradeType + " for " + resourceQuantity + " of resource " +
                    resourceType + ". Total resources price: " + totalResourcesPrice);
        };
    }


    public Consumer<? extends DomainEvent> refillMarketSupply(ResourceMarket resourceMarket) {
        return (RefilledMarketSupply event) -> {
            resourceMarket.addResources(event.getAddedResourceQuantity(), event.getResourceType());

            ResourcePrice updatedPrice = ResourcePrice.of(event.getUpdatedResourcePrice());

            resourceMarket.setResourcePrice(updatedPrice);

            System.out.println("Market supply refilled for resource: " + event.getResourceType() +
                    ". New resource quantity: " + event.getAddedResourceQuantity() +
                    ". Updated price: " + updatedPrice.getValue());
        };
    }

    public Consumer<? extends DomainEvent> updatePrice(ResourceMarket resourceMarket) {
        return (UpdatedResourcePrice event) -> {
            ResourcePrice oldResourcePrice = ResourcePrice.of(event.getOldResourcePrice());
            ResourcePrice newResourcePrice = ResourcePrice.of(event.getNewResourcePrice());

            resourceMarket.updateResourcePrice(event.getId(), event.getResourceType(),
                    oldResourcePrice.getValue(), newResourcePrice.getValue());

            System.out.println("Resource price updated for resource: " + event.getResourceType() +
                    ". Old price: " + oldResourcePrice.getValue() + ". New price: " + newResourcePrice.getValue());
        };
    }

}
