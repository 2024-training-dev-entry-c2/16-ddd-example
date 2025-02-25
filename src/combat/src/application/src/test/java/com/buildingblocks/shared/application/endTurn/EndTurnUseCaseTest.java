package com.buildingblocks.shared.application.endTurn;

import com.buildingblocks.shared.application.combat.endTurn.EndTurnRequest;
import com.buildingblocks.shared.application.combat.endTurn.EndTurnUseCase;
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
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;

class EndTurnUseCaseTest {
    private IEventsRepositoryPort repository;
    private EndTurnUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepositoryPort.class);
        useCase = new EndTurnUseCase(repository);
    }

    @Test
    void executeSuccess() {
        // Arrange
        String aggregateId = "combat123";


        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.empty());
        doNothing().when(repository).save(any(DomainEvent.class));


        EndTurnRequest request = new EndTurnRequest(aggregateId);


        Mono<CombatResponse> result = useCase.execute(request);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(aggregateId, response.getCombatId());
                    assertNotNull(response.getCharacterTeam());
                    assertEquals(0, response.getCharacterTeam().size());
                    assertNotNull(response.getEnemies());
                    assertEquals(0, response.getEnemies().size());
                })
                .verifyComplete();


        Mockito.verify(repository).findEventsByAggregateId(aggregateId);
        Mockito.verify(repository, atLeastOnce()).save(any(DomainEvent.class));
    }
}