package com.buildingblocks.shared.application.discardCard;

import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardAdded;
import com.buildingblocks.shared.application.deckOfCards.discardCard.DiscardCardRequest;
import com.buildingblocks.shared.application.deckOfCards.discardCard.DiscardCardUseCase;
import com.buildingblocks.shared.application.shared.ports.IEventsRepositoryPort;
import com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsResponse;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;

class DiscardCardUseCaseTest {
    private IEventsRepositoryPort repository;
    private DiscardCardUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepositoryPort.class);
        useCase = new DiscardCardUseCase(repository);
    }

    @Test
    void executeSuccess() {
        String aggregateId = "aggregate123";
        String cardId = "card567";

        CardAdded cardAddedEvent = new CardAdded(cardId, "Fireball", 5, "Burn", 3, 10, 1, 2);

        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.just(cardAddedEvent));

        DiscardCardRequest request = new DiscardCardRequest(aggregateId, cardId);

        Mono<DeckOfCardsResponse> result = useCase.execute(request);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(aggregateId, response.getDeckId());
                    Map<String, Object> eventDetails = (Map<String, Object>) response.getEventDetails();
                    assertNotNull(eventDetails);
                    assertEquals(cardId, eventDetails.get("cardId"));
                    assertNotNull(response.getDiscardedCards());
                    assertTrue(response.getDiscardedCards().contains(cardId));
                })
                .verifyComplete();

        Mockito.verify(repository).findEventsByAggregateId(aggregateId);
        Mockito.verify(repository, atLeastOnce()).save(any(DomainEvent.class));
    }
    }