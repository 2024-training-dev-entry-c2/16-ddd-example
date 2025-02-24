package com.buildingblocks.shared.application.recoverCard;

import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardAdded;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardDiscarded;
import com.buildingblocks.shared.application.deckOfCards.recoverCard.RecoverCardRequest;
import com.buildingblocks.shared.application.deckOfCards.recoverCard.RecoverCardUseCase;
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

class RecoverCardUseCaseTest {

    private IEventsRepository repository;
    private RecoverCardUseCase useCase;

    @BeforeEach
    void setUp() {

        repository = Mockito.mock(IEventsRepository.class);
        useCase = new RecoverCardUseCase(repository);
        Mockito.reset(repository);
    }
    @Test
    void executeSuccess() {
        String aggregateId = "aggregate123";
        String cardId = "card567";
        String cardId2 = "card5672";

        CardAdded cardAddedEvent = new CardAdded(cardId, "Fireball", 5, "Burn", 3, 10, 1, 2);
        CardAdded cardAddedEvent2 = new CardAdded(cardId2, "Fireball", 5, "Burn", 3, 10, 1, 2);
        CardDiscarded cardDiscardedEvent = new CardDiscarded(cardId);
        Mockito.when(repository.findEventsByAggregateId(aggregateId)).thenReturn(Flux.just(cardAddedEvent, cardAddedEvent2, cardDiscardedEvent));

        RecoverCardRequest request = new RecoverCardRequest(aggregateId, cardId);
        Mono<DeckOfCardsResponse> result = useCase.execute(request);
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(aggregateId, response.getDeckId());
                    Map<String, Object> eventDetails = (Map<String, Object>) response.getEventDetails();
                    assertNotNull(eventDetails);
                    assertEquals(cardId, eventDetails.get("cardId"));
                    assertEquals(cardId, response.getSkillCards().stream().filter(c -> c == cardId).findFirst().get());
                    assertNotNull(response.getSkillCards());
                    assertTrue(response.getSkillCards().contains(cardId));
                })
                .verifyComplete();

        Mockito.verify(repository).findEventsByAggregateId(aggregateId);
        Mockito.verify(repository, atLeastOnce()).save(any(DomainEvent.class));

    }
}