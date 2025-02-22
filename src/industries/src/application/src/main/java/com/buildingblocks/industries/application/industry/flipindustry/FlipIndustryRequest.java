package com.buildingblocks.industries.application.industry.flipindustry;

import com.buildingblocks.shared.application.Request;

public class FlipIndustryRequest extends Request {
    private final String id;
    private final String location;
    private final String requiredResource;
    private final Integer quantityRequiredResource;
    private final Integer income;

    public FlipIndustryRequest(String aggregateId, String id, String location, String requiredResource, Integer quantityRequiredResource, Integer income) {
        super(aggregateId);
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
