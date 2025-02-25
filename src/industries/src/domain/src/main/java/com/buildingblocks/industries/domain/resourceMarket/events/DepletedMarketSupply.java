package com.buildingblocks.industries.domain.resourceMarket.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class DepletedMarketSupply extends DomainEvent {
    private String id;
    private String resourceType;
    private Integer updatedAvailableResources;

    public DepletedMarketSupply() {
        super(EventsEnum.DEPLETED_MARKET_SUPPLY.name());
    }

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

    public void setId(String id) {
        this.id = id;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setUpdatedAvailableResources(Integer updatedAvailableResources) {
        this.updatedAvailableResources = updatedAvailableResources;
    }
}
