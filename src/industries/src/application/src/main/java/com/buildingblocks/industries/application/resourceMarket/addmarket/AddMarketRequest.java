package com.buildingblocks.industries.application.resourceMarket.addmarket;

import com.buildingblocks.shared.application.Request;

import java.util.List;

public class AddMarketRequest extends Request {
    private List<String> availableResources;
    private Integer resourcePrice;
    private Integer resourceQuantity;
    private String resourceType;

    public AddMarketRequest(List<String> availableResources, Integer resourcePrice, Integer resourceQuantity, String resourceType) {
        super(null);
        this.availableResources = availableResources;
        this.resourcePrice = resourcePrice;
        this.resourceQuantity = resourceQuantity;
        this.resourceType = resourceType;
    }

    public List<String> getAvailableResources() {
        return availableResources;
    }

    public void setAvailableResources(List<String> availableResources) {
        this.availableResources = availableResources;
    }

    public Integer getResourcePrice() {
        return resourcePrice;
    }

    public void setResourcePrice(Integer resourcePrice) {
        this.resourcePrice = resourcePrice;
    }

    public Integer getResourceQuantity() {
        return resourceQuantity;
    }

    public void setResourceQuantity(Integer resourceQuantity) {
        this.resourceQuantity = resourceQuantity;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}
