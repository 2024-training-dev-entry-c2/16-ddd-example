package com.buildingblocks.shared.application.restCards;

import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardAdded;
import com.buildingblocks.shared.application.deckOfCards.restCards.restCardRequest;
import com.buildingblocks.shared.application.deckOfCards.restCards.restCardUseCase;
import com.buildingblocks.shared.application.shared.IEventsRepository;
import com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class restCardUseCaseTest {
    private IEventsRepository repository;
    private restCardUseCase useCase;

    @BeforeEach
    void setUp(){
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new restCardUseCase(repository);
    }
    @Test
    void executeSuccess() {
        String aggregateId = "aggregate123";
        String cardId1 = "card567";
        String cardId2 = "card890";

        CardAdded cardAddedEvent1 = new CardAdded(cardId1, "Fireball", 5, "Burn", 3, 10, 1, 2);
        CardAdded cardAddedEvent2 = new CardAdded(cardId2, "Ice Blast", 3, "Freeze", 2, 8, 1, 2);

        Mockito.when(repository.findEventsByAggregateId(aggregateId)).thenReturn(
                Flux.just(
                        cardAddedEvent1,
                        cardAddedEvent2
                ));
        restCardRequest request = new restCardRequest(aggregateId, false);
        Mono<DeckOfCardsResponse> result = useCase.execute(request);
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(aggregateId, response.getDeckId());
                    assertNotNull(response.getSkillCards());
                    assertEquals(4, response.getSkillCards().size());
                })
                .verifyComplete();
        Mockito.verify(repository).findEventsByAggregateId(aggregateId);
    }
}