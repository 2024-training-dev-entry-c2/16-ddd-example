package com.buildingblocks.shared.application.addEnemy;

import com.buildingblocks.shared.application.combat.addEnemy.AddEnemyRequest;
import com.buildingblocks.shared.application.combat.addEnemy.AddEnemyUseCase;
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

class AddEnemyRequestTest {
    private IEventsRepositoryPort repository;
    private AddEnemyUseCase useCase;
    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepositoryPort.class);
        useCase = new AddEnemyUseCase(repository);
    }
    @Test
    void executeSuccess() {
        String aggregateId = "aggregate123";
        String name = "Goblin";
        Integer heal = 50;
        Integer initiative = 10;

        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.empty());
        AddEnemyRequest request = new AddEnemyRequest(aggregateId, "goblin-1",name, heal, initiative);
        Mono<CombatResponse> result = useCase.execute(request);
        StepVerifier.create(result)
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(aggregateId, response.getCombatId());
                    assertEquals("goblin-1", response.getEnemies().get(0).getName());
                    assertEquals(heal, response.getEnemies().get(0).getHealth());
                    assertEquals(initiative, response.getEnemies().get(0).getInitiative());
                    assertNotNull(response.getEnemies());
                    assertEquals(1, response.getEnemies().size());
                })
                .verifyComplete();

        Mockito.verify(repository).findEventsByAggregateId(aggregateId);
        Mockito.verify(repository, Mockito.atLeastOnce()).save(any(DomainEvent.class));
    }


}