package com.buildingblocks.shared.application.loseCard;

import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardAdded;
import com.buildingblocks.shared.application.deckOfCards.loseCard.loseCardRequest;
import com.buildingblocks.shared.application.deckOfCards.loseCard.loseCardUseCase;
import com.buildingblocks.shared.application.shared.IEventsRepository;
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

class loseCardUseCaseTest {
    private IEventsRepository repository;
    private loseCardUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new loseCardUseCase(repository);
    }

    @Test
    void executeSuccess() {
        String aggregateId = "aggregate123";
        String cardId = "card567";
        CardAdded cardAddedEvent = new CardAdded(cardId, "Fireball", 5, "Burn", 3, 10, 1, 2);        // Arrange
        Mockito.when(repository.findEventsByAggregateId(aggregateId)).thenReturn(Flux.just(cardAddedEvent));

        loseCardRequest request = new loseCardRequest(aggregateId, cardId);
        Mono<DeckOfCardsResponse> result = useCase.execute(request);
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(aggregateId, response.getDeckId());
                    Map<String, Object> eventDetails = (Map<String, Object>) response.getEventDetails();
                    assertNotNull(eventDetails);
                    assertEquals(cardId, eventDetails.get("cardId"));
                    assertNotNull(response.getLostCards());
                    assertTrue(response.getLostCards().contains(cardId));
                })
                .verifyComplete();

        Mockito.verify(repository).findEventsByAggregateId(aggregateId);
        Mockito.verify(repository, atLeastOnce()).save(any(DomainEvent.class));
    }
}