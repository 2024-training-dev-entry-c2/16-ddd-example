package com.buildingblocks.industries.application.resourceMarket.executetrade;

import com.buildingblocks.shared.application.Request;

public class ExecuteTradeRequest extends Request {
    private String tradeId;
    private String resourceId;
    private String tradeType;
    private String resourceType;
    private Integer totalResourcesPrice;
    private Integer resourceQuantity;
    private Integer oldResourcePrice;
    private Integer newResourcePrice;

    public ExecuteTradeRequest() {
        super(null);
    }

    public ExecuteTradeRequest(String aggregateId, String tradeId, String resourceId, String tradeType, String resourceType, Integer totalResourcesPrice, Integer resourceQuantity, Integer oldResourcePrice, Integer newResourcePrice) {
        super(aggregateId);
        this.tradeId = tradeId;
        this.resourceId = resourceId;
        this.tradeType = tradeType;
        this.resourceType = resourceType;
        this.totalResourcesPrice = totalResourcesPrice;
        this.resourceQuantity = resourceQuantity;
        this.oldResourcePrice = oldResourcePrice;
        this.newResourcePrice = newResourcePrice;
    }

    public String getTradeId() {
        return tradeId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public String getResourceType() {
        return resourceType;
    }

    public Integer getTotalResourcesPrice() {
        return totalResourcesPrice;
    }

    public Integer getResourceQuantity() {
        return resourceQuantity;
    }

    public Integer getOldResourcePrice() {
        return oldResourcePrice;
    }

    public Integer getNewResourcePrice() {
        return newResourcePrice;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setTotalResourcesPrice(Integer totalResourcesPrice) {
        this.totalResourcesPrice = totalResourcesPrice;
    }

    public void setResourceQuantity(Integer resourceQuantity) {
        this.resourceQuantity = resourceQuantity;
    }

    public void setOldResourcePrice(Integer oldResourcePrice) {
        this.oldResourcePrice = oldResourcePrice;
    }

    public void setNewResourcePrice(Integer newResourcePrice) {
        this.newResourcePrice = newResourcePrice;
    }
}
