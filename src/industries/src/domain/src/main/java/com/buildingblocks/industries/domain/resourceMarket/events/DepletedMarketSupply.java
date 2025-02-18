package com.buildingblocks.industries.domain.resourceMarket.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class DepletedMarketSupply extends DomainEvent {
    private final String id;
    private final String resourceType;
    private final Integer updatedAvailableResources;

    public DepletedMarketSupply(String id, String resourceType, Integer updatedAvailableResources) {
        super(EventsEnum.DEPLETED_MARKET_SUPPLY.name());
        this.id = id;
        this.resourceType = resourceType;
        this.updatedAvailableResources = updatedAvailableResources;
    }

    public String getId() {
        return id;
    }

    public String getResourceType() {
        return resourceType;
    }

    public Integer getUpdatedAvailableResources() {
        return updatedAvailableResources;
    }
}
