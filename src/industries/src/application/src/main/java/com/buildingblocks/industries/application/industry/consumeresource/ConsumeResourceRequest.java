package com.buildingblocks.industries.application.industry.consumeresource;

import com.buildingblocks.shared.application.Request;

public class ConsumeResourceRequest extends Request {
    private final String id;
    private final String requiredResource;
    private final Integer quantityRequiredResource;

    public ConsumeResourceRequest(String aggregateId, String id, String requiredResource, Integer quantityRequiredResource) {
        super(aggregateId);
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
