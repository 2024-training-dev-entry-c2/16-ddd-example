package com.buildingblocks.industries.domain.resourceMarket.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.List;

public class AddedMarket extends DomainEvent {
    private List<String> availableResources;
    private Integer resourcePrice;
    private Integer resourceQuantity;
    private String resourceType;

    public AddedMarket() {
        super(EventsEnum.ADDED_MARKET.name());
    }

    public AddedMarket(List<String> availableResources, Integer resourcePrice, Integer resourceQuantity, String resourceType) {
        super(EventsEnum.ADDED_MARKET.name());
        this.availableResources = availableResources;
        this.resourcePrice = resourcePrice;
        this.resourceQuantity = resourceQuantity;
        this.resourceType = resourceType;
    }

    public List<String> getAvailableResources() {
        return availableResources;
    }

    public void setAvailableResources(List<String> availableResources) {
        this.availableResources = availableResources;
    }

    public Integer getResourcePrice() {
        return resourcePrice;
    }

    public void setResourcePrice(Integer resourcePrice) {
        this.resourcePrice = resourcePrice;
    }

    public Integer getResourceQuantity() {
        return resourceQuantity;
    }

    public void setResourceQuantity(Integer resourceQuantity) {
        this.resourceQuantity = resourceQuantity;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}
