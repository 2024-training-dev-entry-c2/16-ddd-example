package com.buildingblocks.industries.domain.resourceMarket.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.List;

public class RefilledMarketSupply extends DomainEvent {
    private String id;
    private String resourceType;
    private Integer addedResourceQuantity;
    private List<String> updatedAvailableResources;
    private Integer updatedResourcePrice;

    public RefilledMarketSupply() {
        super(EventsEnum.REFILLED_MARKET_SUPPLY.name());
    }

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

    public void setId(String id) {
        this.id = id;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setAddedResourceQuantity(Integer addedResourceQuantity) {
        this.addedResourceQuantity = addedResourceQuantity;
    }

    public void setUpdatedAvailableResources(List<String> updatedAvailableResources) {
        this.updatedAvailableResources = updatedAvailableResources;
    }

    public void setUpdatedResourcePrice(Integer updatedResourcePrice) {
        this.updatedResourcePrice = updatedResourcePrice;
    }
}
