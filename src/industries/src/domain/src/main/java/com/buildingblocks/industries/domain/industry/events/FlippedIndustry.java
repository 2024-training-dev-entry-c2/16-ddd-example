package com.buildingblocks.industries.domain.industry.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class FlippedIndustry extends DomainEvent {
    private String id;
    private String location;
    private String requiredResource;
    private Integer quantityRequiredResource;
    private Integer income;

    public FlippedIndustry() {
        super(EventsEnum.FLIPPED_INDUSTRY.name());
    }

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

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRequiredResource(String requiredResource) {
        this.requiredResource = requiredResource;
    }

    public void setQuantityRequiredResource(Integer quantityRequiredResource) {
        this.quantityRequiredResource = quantityRequiredResource;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }
}
