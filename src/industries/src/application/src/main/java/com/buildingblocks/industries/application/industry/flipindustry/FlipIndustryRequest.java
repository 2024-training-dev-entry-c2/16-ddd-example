package com.buildingblocks.industries.application.industry.flipindustry;

import com.buildingblocks.shared.application.Request;

public class FlipIndustryRequest extends Request {
    private String id;
    private String location;
    private String requiredResource;
    private Integer quantityRequiredResource;
    private Integer income;

    public FlipIndustryRequest() {
        super(null);
    }

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
