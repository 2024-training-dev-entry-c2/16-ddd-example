package com.buildingblocks.industries.application.resourceMarket.refillmarket;

import com.buildingblocks.shared.application.Request;

import java.util.List;

public class RefillMarketRequest extends Request {
    private String id;
    private String resourceId;
    private String resourceType;
    private Integer addedResourceQuantity;
    private List<String> updatedAvailableResources;
    private Integer updatedResourcePrice;
    private Integer oldResourcePrice;
    private Integer newResourcePrice;

    public RefillMarketRequest() {
        super(null);
    }

    public RefillMarketRequest(String aggregateId, String id, String resourceId, String resourceType, Integer addedResourceQuantity, List<String> updatedAvailableResources, Integer updatedResourcePrice, Integer oldResourcePrice, Integer newResourcePrice) {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setAddedResourceQuantity(Integer addedResourceQuantity) {
        this.addedResourceQuantity = addedResourceQuantity;
    }

    public void setUpdatedAvailableResources(List<String> updatedAvailableResources) {
        this.updatedAvailableResources = updatedAvailableResources;
    }

    public void setUpdatedResourcePrice(Integer updatedResourcePrice) {
        this.updatedResourcePrice = updatedResourcePrice;
    }

    public void setOldResourcePrice(Integer oldResourcePrice) {
        this.oldResourcePrice = oldResourcePrice;
    }

    public void setNewResourcePrice(Integer newResourcePrice) {
        this.newResourcePrice = newResourcePrice;
    }
}
