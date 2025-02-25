package com.buildingblocks.shared.application.addCard;

import com.buildingblocks.shared.application.deckOfCards.addCard.AddCardRequest;
import com.buildingblocks.shared.application.deckOfCards.addCard.AddCardUseCase;
import com.buildingblocks.shared.application.shared.ports.IEventsRepositoryPort;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;

class AddCardUseCaseTest {
    private final AddCardUseCase useCase;

    private final IEventsRepositoryPort repository;

    public AddCardUseCaseTest(){
        repository = Mockito.mock(IEventsRepositoryPort.class);
        useCase = new AddCardUseCase(repository);
    }
    @Test
    void executeSuccess(){
        String aggregateId = "aggregate123";
        String skillCardName = "Fireball";
        int initiative = 5;
        String nameEffect = "Burn";
        int duration = 3;
        int impact = 10;
        int objetives = 1;
        int scope = 2;

        AddCardRequest request = new AddCardRequest(aggregateId, skillCardName, initiative, nameEffect, duration, impact, objetives, scope);
        Mockito.when(repository.findEventsByAggregateId(aggregateId)).thenReturn(Flux.empty());
        doNothing().when(repository).save(any(DomainEvent.class));
        StepVerifier.create(useCase.execute(request))
                .assertNext(response -> {

                    assertNotNull(response);
                    assertEquals(aggregateId, response.getDeckId());
                    assertNotNull(response.getEventDetails());
                    Map<String, Object> eventDetails = (Map<String, Object>) response.getEventDetails();

                    assertEquals(skillCardName, eventDetails.get("cardName"));
                    assertEquals(initiative, eventDetails.get("initiative"));
                })
                .verifyComplete();

        Mockito.verify(repository).findEventsByAggregateId(aggregateId);

        Mockito.verify(repository, atLeastOnce()).save(any(DomainEvent.class));

    }
}