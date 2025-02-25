package com.buildingblocks.industries.domain.resourceMarket.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class ExecutedTrade extends DomainEvent {
    private String id;
    private String tradeType;
    private String resourceType;
    private Integer totalResourcesPrice;
    private Integer resourceQuantity;

    public ExecutedTrade() {
        super(EventsEnum.EXECUTED_TRADE.name());
    }

    public ExecutedTrade(String id, String tradeType, String resourceType, Integer totalResourcesPrice, Integer resourceQuantity) {
        super(EventsEnum.EXECUTED_TRADE.name());
        this.id = id;
        this.tradeType = tradeType;
        this.resourceType = resourceType;
        this.totalResourcesPrice = totalResourcesPrice;
        this.resourceQuantity = resourceQuantity;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
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
}
