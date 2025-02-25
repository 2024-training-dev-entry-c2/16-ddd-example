package com.buildingblocks.industries.application.industry.consumeresource;

import com.buildingblocks.shared.application.Request;

public class ConsumeResourceRequest extends Request {
    private String id;
    private String requiredResource;
    private Integer quantityRequiredResource;

    public ConsumeResourceRequest() {
        super(null);
    }

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
