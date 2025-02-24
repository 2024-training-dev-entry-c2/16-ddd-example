package com.buildingblocks.shared.application.endCombat;


import com.buildingblocks.shared.application.combat.endCombat.EndCombatRequest;
import com.buildingblocks.shared.application.combat.endCombat.EndCombatUseCase;
import com.buildingblocks.shared.application.shared.IEventsRepository;
import com.buildingblocks.shared.application.shared.combat.CombatResponse;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EndCombatUseCaseTest {

    private IEventsRepository repository;
    private EndCombatUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new EndCombatUseCase(repository);
    }

    @Test
    void executeSuccess() {
        String aggregateId = "combat123";

        when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.empty());
        doNothing().when(repository).save(any(DomainEvent.class));

        EndCombatRequest request = new EndCombatRequest(aggregateId);

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

        verify(repository).findEventsByAggregateId(aggregateId);
        verify(repository, atLeastOnce()).save(any(DomainEvent.class));
    }
}