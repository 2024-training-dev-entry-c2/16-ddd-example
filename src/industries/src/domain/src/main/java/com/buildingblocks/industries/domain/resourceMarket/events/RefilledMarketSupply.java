package com.buildingblocks.industries.domain.resourceMarket.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.List;

public class RefilledMarketSupply extends DomainEvent {
    private final String id;
    private final String resourceType;
    private final Integer addedResourceQuantity;
    private final List<String> updatedAvailableResources;
    private final Integer updatedResourcePrice;

    public RefilledMarketSupply(String id, String resourceType, Integer addedResourceQuantity, List<String> updatedAvailableResources, Integer updatedResourcePrice) {
        super(EventsEnum.REFILLED_MARKET_SUPPLY.name());
        this.id = id;
        this.resourceType = resourceType;
        this.addedResourceQuantity = addedResourceQuantity;
        this.updatedAvailableResources = updatedAvailableResources;
        this.updatedResourcePrice = updatedResourcePrice;
    }

    public String getId() {
        return id;
    }

    public String getResourceType() {
        return resourceType;
    }

    public Integer getAddedResourceQuantity() {
        return addedResourceQuantity;
    }

    public Integer getUpdatedResourcePrice() {
        return updatedResourcePrice;
    }

    public List<String> getUpdatedAvailableResources() {
        return updatedAvailableResources;
    }
}
