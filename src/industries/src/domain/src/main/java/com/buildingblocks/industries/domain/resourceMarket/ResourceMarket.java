package com.buildingblocks.industries.domain.resourceMarket;

import com.buildingblocks.industries.domain.industry.IndustryHandler;
import com.buildingblocks.industries.domain.resourceMarket.entities.TradeExchange;
import com.buildingblocks.industries.domain.resourceMarket.events.DepletedMarketSupply;
import com.buildingblocks.industries.domain.resourceMarket.events.ExecutedTrade;
import com.buildingblocks.industries.domain.resourceMarket.events.RefilledMarketSupply;
import com.buildingblocks.industries.domain.resourceMarket.events.UpdatedResourcePrice;
import com.buildingblocks.industries.domain.resourceMarket.values.AvailableResources;
import com.buildingblocks.industries.domain.resourceMarket.values.ResourceMarketId;
import com.buildingblocks.industries.domain.resourceMarket.values.ResourcePrice;
import com.buildingblocks.shared.domain.generic.AggregateRoot;

import java.util.List;

public class ResourceMarket extends AggregateRoot<ResourceMarketId> {
    private TradeExchange tradeExchange;
    private AvailableResources availableResources;
    private ResourcePrice resourcePrice;

    // region Constructors
    public ResourceMarket() {
        super(new ResourceMarketId());
        subscribe(new ResourceMarketHandler(this));
    }

    private ResourceMarket(ResourceMarketId identity) {
        super(identity);
    }
    // endregion

    // region Getters and Setters
    public TradeExchange getTradeExchange() {
        return tradeExchange;
    }

    public void setTradeExchange(TradeExchange tradeExchange) {
        this.tradeExchange = tradeExchange;
    }

    public AvailableResources getAvailableResources() {
        return availableResources;
    }

    public void setAvailableResources(AvailableResources availableResources) {
        this.availableResources = availableResources;
    }

    public ResourcePrice getResourcePrice() {
        return resourcePrice;
    }

    public void setResourcePrice(ResourcePrice resourcePrice) {
        this.resourcePrice = resourcePrice;
    }
    // endregion

    // region Domain Actions
    public void depleteMarketSupply(String id, String resourceType, Integer updatedAvailableResources) {
        apply(new DepletedMarketSupply(id, resourceType, updatedAvailableResources));
    }

    public void executeTrade(String id, String tradeType, String resourceType, Integer totalResourcesPrice, Integer resourceQuantity) {
        apply(new ExecutedTrade(id, tradeType, resourceType, totalResourcesPrice, resourceQuantity));
    }

    public void refillMarketSupply(String id, String resourceType, Integer addedResourceQuantity, List<String> updatedAvailableResources, Integer updatedResourcePrice) {
        apply(new RefilledMarketSupply(id, resourceType, addedResourceQuantity, updatedAvailableResources, updatedResourcePrice));
    }

    public void updateResourcePrice(String id, String resourceType, Integer oldResourcePrice, Integer newResourcePrice) {
        apply(new UpdatedResourcePrice(id, resourceType, oldResourcePrice, newResourcePrice));
    }
    // endregion

    // region Public Methods
    public void addResources(int quantityToAdd, String resourceType) {
        AvailableResources updatedResources = availableResources.increaseQuantity(quantityToAdd, resourceType);
        setAvailableResources(updatedResources);
    }
    // endregion
}
