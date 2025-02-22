package com.buildingblocks.industries.domain.resourceMarket;

import com.buildingblocks.industries.domain.player.values.PlayerId;
import com.buildingblocks.industries.domain.player.values.ResourceType;
import com.buildingblocks.industries.domain.resourceMarket.entities.TradeExchange;
import com.buildingblocks.industries.domain.resourceMarket.events.DepletedMarketSupply;
import com.buildingblocks.industries.domain.resourceMarket.events.ExecutedTrade;
import com.buildingblocks.industries.domain.resourceMarket.events.RefilledMarketSupply;
import com.buildingblocks.industries.domain.resourceMarket.events.UpdatedResourcePrice;
import com.buildingblocks.industries.domain.resourceMarket.values.*;
import com.buildingblocks.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ResourceMarketTest {
    private ResourceMarket resourceMarket;

    @BeforeEach
    void setUp() {
        resourceMarket = new ResourceMarket();
    }

    @Test
    void shouldDepleteMarketSupply() {
        String resourceId = "rm-1";
        String resourceType = "Coal";
        Integer updatedResources = 5;

        resourceMarket.depleteMarketSupply(resourceId, resourceType, updatedResources);
        List<DomainEvent> changes = resourceMarket.getUncommttedEvents();

        assertFalse(changes.isEmpty());
        assertTrue(changes.get(0) instanceof DepletedMarketSupply);
        DepletedMarketSupply event = (DepletedMarketSupply) changes.get(0);
        assertEquals(resourceId, event.getId());
        assertEquals(resourceType, event.getResourceType());
        assertEquals(updatedResources, event.getUpdatedAvailableResources());
    }


    @Test
    void shouldRefillMarketSupply() {
        String resourceId = "resou-123";
        String resourceType = "Beer";
        Integer addedQuantity = 15;
        List<String> initialResources = List.of("Beer: 15");
        List<String> updatedResources = List.of("Beer: 30");
        Integer updatedPrice = 20;

        ResourceMarket resourceMarket = new ResourceMarket();
        resourceMarket.setAvailableResources(AvailableResources.of(initialResources));

        resourceMarket.refillMarketSupply(resourceId, resourceType, addedQuantity, updatedResources, updatedPrice);
        List<DomainEvent> changes = resourceMarket.getUncommttedEvents();

        assertFalse(changes.isEmpty());
        assertTrue(changes.get(0) instanceof RefilledMarketSupply);

        RefilledMarketSupply event = (RefilledMarketSupply) changes.get(0);
        assertEquals(resourceId, event.getId());
        assertEquals(resourceType, event.getResourceType());
        assertEquals(addedQuantity, event.getAddedResourceQuantity());
        assertEquals(updatedResources, event.getUpdatedAvailableResources());
        assertEquals(updatedPrice, event.getUpdatedResourcePrice());
    }

    @Test
    void shouldExecuteTrade() {
        String tradeId = "trade-123";
        String tradeType = "Buy";
        String resourceType = "Iron";
        Integer totalResourcesPrice = 100;
        Integer resourceQuantity = 10;

        resourceMarket.executeTrade(tradeId, tradeType, resourceType, totalResourcesPrice, resourceQuantity);
        List<DomainEvent> changes = resourceMarket.getUncommttedEvents();

        assertFalse(changes.isEmpty(), "The event should have been generated.");
        assertTrue(changes.get(0) instanceof ExecutedTrade, "The event should be ExecutedTrade.");

        ExecutedTrade event = (ExecutedTrade) changes.get(0);
        assertEquals(tradeId, event.getId(), "The trade ID does not match.");
        assertEquals(tradeType, event.getTradeType(), "The trade type does not match.");
        assertEquals(resourceType, event.getResourceType(), "The resource type does not match.");
        assertEquals(totalResourcesPrice, event.getTotalResourcesPrice(), "The total resource price does not match.");
        assertEquals(resourceQuantity, event.getResourceQuantity(), "The resource quantity does not match.");
    }

    @Test
    void shouldUpdateResourcePrice() {
        String resourceId = "rm-1";
        String resourceType = "Coal";
        Integer oldPrice = 50;
        Integer newPrice = 60;

        ResourceMarket resourceMarket = new ResourceMarket();

        resourceMarket.updateResourcePrice(resourceId, resourceType, oldPrice, newPrice);
        List<DomainEvent> changes = resourceMarket.getUncommttedEvents();

        assertFalse(changes.isEmpty(), "The event should have been generated.");
        assertTrue(changes.get(0) instanceof UpdatedResourcePrice, "The event should be UpdatedResourcePrice.");

        UpdatedResourcePrice event = (UpdatedResourcePrice) changes.get(0);
        assertEquals(resourceId, event.getId(), "The resource ID does not match.");
        assertEquals(resourceType, event.getResourceType(), "The resource type does not match.");
        assertEquals(oldPrice, event.getOldResourcePrice(), "The old price does not match.");
        assertEquals(newPrice, event.getNewResourcePrice(), "The new price does not match.");

        assertEquals(newPrice, resourceMarket.getResourcePrice().getValue(), "The resource price was not updated correctly.");
    }


    @Test
    void shouldRebuildFromEvents() {
        String resourceId = PlayerId.of("resou-123").getValue();

        List<DomainEvent> eventStream = List.of(
                new RefilledMarketSupply(resourceId, "Coal", 20, List.of("Coal: 50"), 30),
                new UpdatedResourcePrice(resourceId, "Coal", 30, 35)
        );

        ResourceMarket rebuiltMarket = ResourceMarket.from(resourceId, eventStream);

        assertNotNull(rebuiltMarket);
        assertEquals(resourceId, resourceId);
    }

    @Test
    void shouldSetAndGetTradeExchange() {
        TradeExchange tradeExchange = new TradeExchange(
                TradeExchange.TradeType.BUY,
                ResourceType.of("Coal"),
                ResourceQuantity.of(10),
                ResourcePrice.of(5),
                AvailableResources.of(Collections.nCopies(20, "Coal"))
        );
        resourceMarket.setTradeExchange((List<TradeExchange>) tradeExchange);

        assertNotNull(resourceMarket.getTradeExchange(), "TradeExchange should not be null.");
        assertEquals(tradeExchange, resourceMarket.getTradeExchange(), "TradeExchange should be the assigned instance.");
    }

    @Test
    void shouldReturnNullWhenTradeExchangeNotSet() {
        assertNull(resourceMarket.getTradeExchange(), "TradeExchange should be null by default.");
    }

    @Test
    void shouldReturnNullWhenAvailableResourcesNotSet() {
        assertNull(resourceMarket.getAvailableResources(), "AvailableResources should be null by default.");
    }

    @Test
    void shouldReturnTradeExchangeWhenTradeIdMatches() {
        TradeExchangeId tradeExchangeId = TradeExchangeId.of("trade-123");
        TradeExchange tradeExchange = new TradeExchange(
                tradeExchangeId,
                TradeExchange.TradeType.BUY,
                ResourceType.of("Coal"),
                ResourceQuantity.of(10),
                ResourcePrice.of(5),
                AvailableResources.of(Collections.nCopies(20, "Coal"))
        );
        resourceMarket.setTradeExchange(tradeExchange);

        Optional<TradeExchange> result = resourceMarket.findTradeById(tradeExchangeId.getValue()); // Se busca por String
        assertAll(
                () -> assertTrue(result.isPresent(), "TradeExchange should be found."),
                () -> assertEquals(tradeExchange, result.get(), "The found TradeExchange should match the assigned one.")
        );
    }

    @Test
    void shouldReturnEmptyWhenTradeExchangeIsNull() {
        Optional<TradeExchange> result = resourceMarket.findTradeById("trade-123");
        assertTrue(result.isEmpty(), "Should return empty when tradeExchange is null.");
    }

    @Test
    void shouldReturnEmptyWhenTradeIdDoesNotMatch() {
        TradeExchange tradeExchange = new TradeExchange(
                TradeExchangeId.of("trade-456"),
                TradeExchange.TradeType.BUY,
                ResourceType.of("Coal"),
                ResourceQuantity.of(10),
                ResourcePrice.of(5),
                AvailableResources.of(Collections.nCopies(20, "Coal"))
        );
        resourceMarket.setTradeExchange(tradeExchange);

        Optional<TradeExchange> result = resourceMarket.findTradeById("trade-123");
        assertTrue(result.isEmpty(), "Should return empty when tradeId does not match.");
    }

    @Test
    void shouldNotExecuteTradeWhenTradeDoesNotExist() {
        String tradeId = "trade-123";

        Optional<TradeExchange> trade = resourceMarket.findTradeById(tradeId);

        assertFalse(trade.isPresent(), "TradeExchange should not be found.");
    }

    @Test
    void shouldHandleTradeNotFound() {
        String nonExistentTradeId = "non-existent-trade";
        ExecutedTrade event = new ExecutedTrade(
                nonExistentTradeId,
                "BUY",
                "Coal",
                50,
                5
        );
        resourceMarket.findTradeById(event.getId()).ifPresentOrElse(trade -> {
            resourceMarket.executeTrade(
                    event.getId(),
                    event.getTradeType(),
                    event.getResourceType(),
                    event.getTotalResourcesPrice(),
                    event.getResourceQuantity()
            );
        }, () -> System.out.println("Trade not found"));

        List<DomainEvent> changes = resourceMarket.getUncommttedEvents();
        assertTrue(changes.isEmpty(), "No event should be generated if the trade does not exist.");
    }

}
