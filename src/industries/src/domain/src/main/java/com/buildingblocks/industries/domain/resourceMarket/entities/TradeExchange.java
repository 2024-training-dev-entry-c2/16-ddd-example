package com.buildingblocks.industries.domain.resourceMarket.entities;

import com.buildingblocks.industries.domain.player.values.ResourceType;
import com.buildingblocks.industries.domain.resourceMarket.values.*;
import com.buildingblocks.shared.domain.generic.Entity;

public class TradeExchange extends Entity<TradeExchangeId> {
    private TradeType tradeType;
    private ResourceType resourceType;
    private ResourceQuantity resourceQuantity;
    private ResourcePrice resourcePrice;
    private AvailableResources availableResources;

    // region Constructors
    public TradeExchange(TradeType tradeType, ResourceType resourceType, ResourceQuantity resourceQuantity, ResourcePrice resourcePrice, AvailableResources availableResources) {
        super(new TradeExchangeId());
        this.tradeType = tradeType;
        this.resourceType = resourceType;
        this.resourceQuantity = resourceQuantity;
        this.resourcePrice = resourcePrice;
        this.availableResources = availableResources;
    }

    public TradeExchange(TradeExchangeId identity, TradeType tradeType, ResourceType resourceType, ResourceQuantity resourceQuantity, ResourcePrice resourcePrice, AvailableResources availableResources) {
        super(identity);
        this.tradeType = tradeType;
        this.resourceType = resourceType;
        this.resourceQuantity = resourceQuantity;
        this.resourcePrice = resourcePrice;
        this.availableResources = availableResources;
    }
    // endregion

    // region Public Methods
    public AvailableResources executeTrade(AvailableResources availableResources, Integer playerBudget) {
        Integer totalPrice = calculateTotalPrice();
        if (this.tradeType == TradeType.SELL) return getResourcesToSell(availableResources);
        if (this.tradeType == TradeType.BUY) return getResourcesToBuy(availableResources, playerBudget, totalPrice);
        return availableResources;
    }

    public Integer calculateTotalPrice() {
        return this.resourceQuantity.getValue() * this.resourcePrice.getValue();
    }

    public enum TradeType {
        BUY, SELL;
    }
    // endregion

    // region Private Methods
    private AvailableResources getResourcesToBuy(AvailableResources availableResources, Integer playerBudget, Integer totalPrice) {
        if (playerBudget >= totalPrice) {
            return availableResources.increaseQuantity(this.resourceQuantity.getValue(), this.resourceType.getValue());
        } else {
            System.out.println("Not enough to buy");
            return availableResources;
        }
    }

    private AvailableResources getResourcesToSell(AvailableResources availableResources) {
        if (this.resourceQuantity.getValue() <= availableResources.getValue().size()) {
            return availableResources.reduceQuantity(this.resourceQuantity.getValue());
        } else {
            System.out.println("Not enough to sell");
            return availableResources;
        }
    }
    // endregion

    // region Getters and Setters
    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public ResourceQuantity getResourceQuantity() {
        return resourceQuantity;
    }

    public void setResourceQuantity(ResourceQuantity resourceQuantity) {
        this.resourceQuantity = resourceQuantity;
    }

    public ResourcePrice getResourcePrice() {
        return resourcePrice;
    }

    public void setResourcePrice(ResourcePrice resourcePrice) {
        this.resourcePrice = resourcePrice;
    }

    public AvailableResources getAvailableResources() {
        return availableResources;
    }

    public void setAvailableResources(AvailableResources availableResources) {
        this.availableResources = availableResources;
    }
    // endregion
}
