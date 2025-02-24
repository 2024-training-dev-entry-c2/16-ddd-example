package com.buildingblocks.shared.application.reorganizeDeck;

import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardAdded;
import com.buildingblocks.shared.application.deckOfCards.reorganizeDeck.ReorganizeDeckRequest;
import com.buildingblocks.shared.application.deckOfCards.reorganizeDeck.ReorganizeDeckUseCase;
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
import static org.mockito.Mockito.doNothing;

class ReorganizeDeckUseCaseTest {

    private IEventsRepository repository;
    private ReorganizeDeckUseCase useCase;

    @BeforeEach
    void setUp(){
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new ReorganizeDeckUseCase(repository);

    }

    @Test
    void executeSuccess() {
        String aggregateId = "aggregate123";
        String cardId1 = "card567";
        String cardId2 = "card890";

        CardAdded cardAddedEvent1 = new CardAdded(cardId1, "Fireball", 5, "Burn", 3, 10, 1, 2);
        CardAdded cardAddedEvent2 = new CardAdded(cardId2, "Ice Blast", 3, "Freeze", 2, 8, 1,2);

        Mockito.when(repository.findEventsByAggregateId(aggregateId)).thenReturn(
                Flux.just(
                        cardAddedEvent1,
                        cardAddedEvent2
                ));
        doNothing().when(repository).save(any(DomainEvent.class));
        ReorganizeDeckRequest request = new ReorganizeDeckRequest(aggregateId);
        Mono<DeckOfCardsResponse> result = useCase.execute(request);
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertNotNull(response);

                    assertEquals(aggregateId, response.getDeckId());

                    Map<String, Object> eventDetails = (Map<String, Object>) response.getEventDetails();
                    assertNotNull(eventDetails);
                    assertEquals(true, eventDetails.get("reorganized"));

                    assertNotNull(response.getSkillCards());
                    assertEquals(4, response.getSkillCards().size());
                })
                .verifyComplete();
        Mockito.verify(repository).findEventsByAggregateId(aggregateId);
        Mockito.verify(repository, Mockito.atLeastOnce()).save(any(DomainEvent.class));
    }

}