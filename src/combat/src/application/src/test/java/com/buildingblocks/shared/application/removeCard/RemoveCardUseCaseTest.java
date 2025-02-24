package com.buildingblocks.shared.application.removeCard;

import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardAdded;
import com.buildingblocks.shared.application.deckOfCards.removeCard.RemoveCardRequest;
import com.buildingblocks.shared.application.deckOfCards.removeCard.RemoveCardUseCase;
import com.buildingblocks.shared.application.shared.IEventsRepository;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;

class RemoveCardUseCaseTest {

    private IEventsRepository repository;
    private RemoveCardUseCase useCase;
    @BeforeEach
    void setUp(){
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new RemoveCardUseCase(repository);
    }
    @Test
    void executeSuccess() {
        String aggregateId = "aggregate123";
        String cardId = "card567";

        CardAdded cardAddedEvent = new CardAdded(cardId, "Fireball", 5, "Burn", 3, 10, 1, 2);
        RemoveCardRequest request = new RemoveCardRequest(aggregateId, cardAddedEvent.getCardId());
        Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
                .thenReturn(Flux.just(cardAddedEvent));


        doNothing().when(repository).save(any(DomainEvent.class));

        StepVerifier.create(useCase.execute(request))
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(aggregateId, response.getDeckId());
                    assertNotNull(response.getEventDetails());

                    Map<String, Object> eventDetails = (Map<String, Object>) response.getEventDetails();
                    assertEquals(cardId, eventDetails.get("cardId"));
                })
                .verifyComplete();

        Mockito.verify(repository).findEventsByAggregateId(aggregateId);
        Mockito.verify(repository, atLeastOnce()).save(any(DomainEvent.class));
    }

}