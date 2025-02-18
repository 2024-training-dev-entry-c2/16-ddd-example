package com.buildingblocks.industries.domain.industry.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class ConsumedResource extends DomainEvent {
    private final String id;
    private final String requiredResource;
    private final Integer quantityRequiredResource;

    public ConsumedResource(String id, String requiredResource, Integer quantityRequiredResource) {
        super(EventsEnum.CONSUMED_RESOURCE.name());
        this.id = id;
        this.requiredResource = requiredResource;
        this.quantityRequiredResource = quantityRequiredResource;
    }

    public String getId() {
        return id;
    }

    public String getRequiredResource() {
        return requiredResource;
    }

    public Integer getQuantityRequiredResource() {
        return quantityRequiredResource;
    }
}
