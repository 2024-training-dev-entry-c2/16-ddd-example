package com.buildingblocks.industries.application.resourceMarket.depletemarket;

import com.buildingblocks.shared.application.Request;

public class DepleteMarketRequest extends Request {
    private String id;
    private String resourceId;
    private String resourceType;
    private Integer updatedAvailableResources;
    private Integer oldResourcePrice;
    private Integer newResourcePrice;

    public DepleteMarketRequest() {
        super(null);
    }

    public DepleteMarketRequest(String aggregateId, String id, String resourceId, String resourceType, Integer updatedAvailableResources, Integer oldResourcePrice, Integer newResourcePrice) {
        super(aggregateId);
        this.id = id;
        this.resourceId = resourceId;
        this.resourceType = resourceType;
        this.updatedAvailableResources = updatedAvailableResources;
        this.oldResourcePrice = oldResourcePrice;
        this.newResourcePrice = newResourcePrice;
    }

    public String getId() {
        return id;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public Integer getUpdatedAvailableResources() {
        return updatedAvailableResources;
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

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setUpdatedAvailableResources(Integer updatedAvailableResources) {
        this.updatedAvailableResources = updatedAvailableResources;
    }

    public void setOldResourcePrice(Integer oldResourcePrice) {
        this.oldResourcePrice = oldResourcePrice;
    }

    public void setNewResourcePrice(Integer newResourcePrice) {
        this.newResourcePrice = newResourcePrice;
    }
}
