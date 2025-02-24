package com.buildingblocks.shared.application.improveCard;

import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardAdded;
import com.buildingblocks.shared.application.deckOfCards.improveCard.ImproveCardRequest;
import com.buildingblocks.shared.application.deckOfCards.improveCard.ImproveCardUseCase;
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

class ImproveCardUseCaseTest {
    private IEventsRepository repository;
    private ImproveCardUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new ImproveCardUseCase(repository);
    }
    @Test
    void executeSuccess() {
        // Arrange
        String aggregateId = "aggregate123";
        String cardId = "card567";
        String improvement = "Increase damage by 2";

        CardAdded cardAddedEvent = new CardAdded(cardId, "Fireball", 5, "Damage", 3, 10, 1, 2);

        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.just(cardAddedEvent));

        ImproveCardRequest request = new ImproveCardRequest(aggregateId, cardId, improvement);

        Mono<DeckOfCardsResponse> result = useCase.execute(request);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assertNotNull(response);

                    assertEquals(aggregateId, response.getDeckId());

                    Map<String, Object> eventDetails = (Map<String, Object>) response.getEventDetails();
                    assertNotNull(eventDetails);
                    assertEquals(cardId, eventDetails.get("cardId"));
                    assertEquals(improvement, eventDetails.get("improvement"));

                    assertNotNull(response.getSkillCards());
                    assertTrue(response.getSkillCards().contains(cardId));
                })
                .verifyComplete();

        Mockito.verify(repository).findEventsByAggregateId(aggregateId);
        Mockito.verify(repository, atLeastOnce()).save(any(DomainEvent.class));
    }
}