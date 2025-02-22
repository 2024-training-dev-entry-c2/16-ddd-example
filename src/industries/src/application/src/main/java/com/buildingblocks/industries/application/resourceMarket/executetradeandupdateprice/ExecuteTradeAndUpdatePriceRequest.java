package com.buildingblocks.industries.application.resourceMarket.executetradeandupdateprice;

import com.buildingblocks.shared.application.Request;

public class ExecuteTradeAndUpdatePriceRequest extends Request {
    private final String tradeId;
    private final String resourceId;
    private final String tradeType;
    private final String resourceType;
    private final Integer totalResourcesPrice;
    private final Integer resourceQuantity;
    private final Integer oldResourcePrice;
    private final Integer newResourcePrice;

    public ExecuteTradeAndUpdatePriceRequest(String aggregateId, String tradeId, String resourceId, String tradeType, String resourceType, Integer totalResourcesPrice, Integer resourceQuantity, Integer oldResourcePrice, Integer newResourcePrice) {
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
}
