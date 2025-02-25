package com.buildingblocks.industries.domain.industry.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class ConsumedResource extends DomainEvent {
    private String id;
    private String requiredResource;
    private Integer quantityRequiredResource;

    public ConsumedResource() {
        super(EventsEnum.CONSUMED_RESOURCE.name());
    }

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

    public void setId(String id) {
        this.id = id;
    }

    public void setRequiredResource(String requiredResource) {
        this.requiredResource = requiredResource;
    }

    public void setQuantityRequiredResource(Integer quantityRequiredResource) {
        this.quantityRequiredResource = quantityRequiredResource;
    }
}
