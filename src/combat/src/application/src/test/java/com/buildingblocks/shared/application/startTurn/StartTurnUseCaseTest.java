package com.buildingblocks.shared.application.startTurn;

import com.buildingblocks.shared.application.combat.startTurn.StartTurnRequest;
import com.buildingblocks.shared.application.combat.startTurn.StartTurnUseCase;
import com.buildingblocks.shared.application.shared.ports.IEventsRepositoryPort;
import com.buildingblocks.shared.application.shared.combat.CombatResponse;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

class StartTurnUseCaseTest {

    private IEventsRepositoryPort repository;
    private StartTurnUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepositoryPort.class);
        useCase = new StartTurnUseCase(repository);
    }

    @Test
    void executeSuccess() {
        // Arrange
        String aggregateId = "combat123";
        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.empty());
        doNothing().when(repository).save(any(DomainEvent.class));

        StartTurnRequest request = new StartTurnRequest(aggregateId);

        Mono<CombatResponse> result = useCase.execute(request);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(aggregateId, response.getCombatId());

                })
                .verifyComplete();

        Mockito.verify(repository).findEventsByAggregateId(aggregateId);
        Mockito.verify(repository, Mockito.atLeastOnce()).save(any(DomainEvent.class));
    }
}