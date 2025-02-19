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
        add(updateResourcePrice(resourceMarket));
    }

    public Consumer<? extends DomainEvent> depleteMarketSupply(ResourceMarket resourceMarket) {
        return (DepletedMarketSupply event) -> {
            List<String> updatedResourcesList = new ArrayList<>();
            updatedResourcesList.add(String.valueOf(event.getUpdatedAvailableResources()));

            AvailableResources updatedResources = AvailableResources.of(updatedResourcesList);
            resourceMarket.setAvailableResources(updatedResources);
        };
    }

public Consumer<? extends DomainEvent> executeTrade(ResourceMarket resourceMarket) {
    return (ExecutedTrade event) -> {
        String tradeId = event.getId();
        String tradeType = event.getTradeType();
        String resourceType = event.getResourceType();
        Integer totalResourcesPrice = event.getTotalResourcesPrice();
        Integer resourceQuantity = event.getResourceQuantity();
    };
}

    public Consumer<? extends DomainEvent> refillMarketSupply(ResourceMarket resourceMarket) {
        return (RefilledMarketSupply event) -> {
            resourceMarket.addResources(event.getAddedResourceQuantity(), event.getResourceType());
            ResourcePrice updatedPrice = ResourcePrice.of(event.getUpdatedResourcePrice());
            resourceMarket.setResourcePrice(updatedPrice);
        };
    }

    public Consumer<? extends DomainEvent> updateResourcePrice(ResourceMarket resourceMarket) {
        return (UpdatedResourcePrice event) -> {
            ResourcePrice newResourcePrice = ResourcePrice.of(event.getNewResourcePrice());
            resourceMarket.setResourcePrice(newResourcePrice);
        };
    }


}
