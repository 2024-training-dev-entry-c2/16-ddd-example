package com.buildingblocks.industries.application.resourceMarket.depletemarket;

import com.buildingblocks.shared.application.Request;

public class DepleteMarketRequest extends Request {
    private final String id;
    private final String resourceId;
    private final String resourceType;
    private final Integer updatedAvailableResources;
    private final Integer oldResourcePrice;
    private final Integer newResourcePrice;

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
}
