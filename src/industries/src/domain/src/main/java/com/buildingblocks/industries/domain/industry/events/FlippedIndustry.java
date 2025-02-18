package com.buildingblocks.industries.domain.industry.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class FlippedIndustry extends DomainEvent {
    private final String id;
    private final String location;
    private final String requiredResource;
    private final Integer quantityRequiredResource;
    private final Integer income;

    public FlippedIndustry(String id, String location, String requiredResource, Integer quantityRequiredResource, Integer income) {
        super(EventsEnum.FLIPPED_INDUSTRY.name());
        this.id = id;
        this.location = location;
        this.requiredResource = requiredResource;
        this.quantityRequiredResource = quantityRequiredResource;
        this.income = income;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getRequiredResource() {
        return requiredResource;
    }

    public Integer getQuantityRequiredResource() {
        return quantityRequiredResource;
    }

    public Integer getIncome() {
        return income;
    }
}
