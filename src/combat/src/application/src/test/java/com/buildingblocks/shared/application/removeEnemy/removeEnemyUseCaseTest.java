package com.buildingblocks.shared.application.removeEnemy;

import com.buildingblocks.shared.application.combat.domain.combat.events.EnemyAdded;
import com.buildingblocks.shared.application.combat.removeEnemy.RemoveEnemyRequest;
import com.buildingblocks.shared.application.combat.removeEnemy.RemoveEnemyUseCase;
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

class removeEnemyUseCaseTest {

    private IEventsRepositoryPort repository;
    private RemoveEnemyUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepositoryPort.class);
        useCase = new RemoveEnemyUseCase(repository);
    }

    @Test
    void executeSuccess() {
        String aggregateId = "combat123";
        String enemyId = "enemy567";

        EnemyAdded enemyAddedEvent = new EnemyAdded(enemyId,"Goblin", 10, 5);

        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.just(enemyAddedEvent));

        RemoveEnemyRequest request = new RemoveEnemyRequest(aggregateId, enemyId);

        Mono<CombatResponse> result = useCase.execute(request);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assertNotNull(response);

                    assertEquals(aggregateId, response.getCombatId());

                    assertNotNull(response.getEnemies());
                    assertEquals(0, response.getEnemies().size());
                })
                .verifyComplete();


        Mockito.verify(repository).findEventsByAggregateId(aggregateId);
        Mockito.verify(repository, atLeastOnce()).save(any(DomainEvent.class));
    }
}