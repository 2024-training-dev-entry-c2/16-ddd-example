package com.buildingblocks.industries.domain.resourceMarket.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class ExecutedTrade extends DomainEvent {
    private final String id;
    private final String tradeType;
    private final String resourceType;
    private final Integer totalResourcesPrice;
    private final Integer resourceQuantity;

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
}
