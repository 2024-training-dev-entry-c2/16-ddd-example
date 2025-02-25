package com.buildingblocks.industries.domain.resourceMarket.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class UpdatedResourcePrice extends DomainEvent {
    private String id;
    private String resourceType;
    private Integer oldResourcePrice;
    private Integer newResourcePrice;

    public UpdatedResourcePrice() {
        super(EventsEnum.UPDATED_RESOURCE_PRICE.name());
    }

    public UpdatedResourcePrice(String id, String resourceType, Integer oldResourcePrice, Integer newResourcePrice) {
        super(EventsEnum.UPDATED_RESOURCE_PRICE.name());
        this.id = id;
        this.resourceType = resourceType;
        this.oldResourcePrice = oldResourcePrice;
        this.newResourcePrice = newResourcePrice;
    }

    public String getId() {
        return id;
    }

    public String getResourceType() {
        return resourceType;
    }

    public Integer getOldResourcePrice() {
        return oldResourcePrice;
    }

    public Integer getNewResourcePrice() {
        return newResourcePrice;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setOldResourcePrice(Integer oldResourcePrice) {
        this.oldResourcePrice = oldResourcePrice;
    }

    public void setNewResourcePrice(Integer newResourcePrice) {
        this.newResourcePrice = newResourcePrice;
    }
}
