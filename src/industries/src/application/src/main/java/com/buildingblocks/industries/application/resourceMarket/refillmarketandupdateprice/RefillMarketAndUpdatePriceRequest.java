package com.buildingblocks.industries.application.resourceMarket.refillmarketandupdateprice;

import com.buildingblocks.shared.application.Request;

import java.util.List;

public class RefillMarketAndUpdatePriceRequest extends Request {
    private final String id;
    private final String resourceId;
    private final String resourceType;
    private final Integer addedResourceQuantity;
    private final List<String> updatedAvailableResources;
    private final Integer updatedResourcePrice;
    private final Integer oldResourcePrice;
    private final Integer newResourcePrice;

    public RefillMarketAndUpdatePriceRequest(String aggregateId, String id, String resourceId, String resourceType, Integer addedResourceQuantity, List<String> updatedAvailableResources, Integer updatedResourcePrice, Integer oldResourcePrice, Integer newResourcePrice) {
        super(aggregateId);
        this.id = id;
        this.resourceId = resourceId;
        this.resourceType = resourceType;
        this.addedResourceQuantity = addedResourceQuantity;
        this.updatedAvailableResources = updatedAvailableResources;
        this.updatedResourcePrice = updatedResourcePrice;
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

    public Integer getAddedResourceQuantity() {
        return addedResourceQuantity;
    }

    public List<String> getUpdatedAvailableResources() {
        return updatedAvailableResources;
    }

    public Integer getUpdatedResourcePrice() {
        return updatedResourcePrice;
    }

    public Integer getOldResourcePrice() {
        return oldResourcePrice;
    }

    public Integer getNewResourcePrice() {
        return newResourcePrice;
    }
}
