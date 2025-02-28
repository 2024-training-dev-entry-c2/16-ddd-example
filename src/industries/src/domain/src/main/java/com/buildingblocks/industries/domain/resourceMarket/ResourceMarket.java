package com.buildingblocks.industries.domain.resourceMarket;

import com.buildingblocks.industries.domain.resourceMarket.entities.TradeExchange;
import com.buildingblocks.industries.domain.resourceMarket.events.*;
import com.buildingblocks.industries.domain.resourceMarket.values.*;
import com.buildingblocks.shared.domain.generic.AggregateRoot;
import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.List;
import java.util.Optional;

public class ResourceMarket extends AggregateRoot<ResourceMarketId> {
    private List<TradeExchange> tradeExchange;
    private AvailableResources availableResources;
    private ResourcePrice resourcePrice;
    private ResourceQuantity resourceQuantity;
    private ResourceType resourceType;

    // region Constructors
    public ResourceMarket(List<String> availableResources, Integer resourcePrice, Integer resourceQuantity, String resourceType) {
        super(new ResourceMarketId());
        subscribe(new ResourceMarketHandler(this));
        apply(new AddedMarket(availableResources, resourcePrice, resourceQuantity, resourceType));
    }

    private ResourceMarket(ResourceMarketId identity) {
        super(identity);
        subscribe(new ResourceMarketHandler(this));
    }
    // endregion

    // region Getters and Setters
    public List<TradeExchange> getTradeExchange() {
        return tradeExchange;
    }

    public void setTradeExchange(List<TradeExchange> tradeExchange) {
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

    public ResourceQuantity getResourceQuantity() {
        return resourceQuantity;
    }

    public void setResourceQuantity(ResourceQuantity resourceQuantity) {
        this.resourceQuantity = resourceQuantity;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
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

    public Optional<TradeExchange> findTradeById(String tradeId) {
        return tradeExchange.stream()
                .filter(trade -> trade.getIdentity().getValue().equals(tradeId))
                .findFirst();
    }

    public static ResourceMarket from(final String identity, final List<DomainEvent> events) {
        ResourceMarket resourceMarket = new ResourceMarket(ResourceMarketId.of(identity));

        events.forEach(resourceMarket::apply);
        return resourceMarket;
    }
    // endregion
}
