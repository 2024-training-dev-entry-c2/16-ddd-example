package com.buildingblocks.industries.domain.resourceMarket.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class UpdatedResourcePrice extends DomainEvent {
    private final String id;
    private final String resourceType;
    private final Integer oldResourcePrice;
    private final Integer newResourcePrice;

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
}
