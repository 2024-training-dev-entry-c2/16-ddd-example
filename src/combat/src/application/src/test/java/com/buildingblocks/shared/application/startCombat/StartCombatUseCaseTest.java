package com.buildingblocks.shared.application.startCombat;

import com.buildingblocks.shared.application.combat.startCombat.StartCombatRequest;
import com.buildingblocks.shared.application.combat.startCombat.StartCombatUseCase;
import com.buildingblocks.shared.application.shared.IEventsRepository;
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

class StartCombatUseCaseTest {
    private IEventsRepository repository;
    private StartCombatUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new StartCombatUseCase(repository);
    }

    @Test
    void executeSuccess() {

        String aggregateId = "combat123";
        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.empty());
        doNothing().when(repository).save(any(DomainEvent.class));

        StartCombatRequest request = new StartCombatRequest(aggregateId);

        Mono<CombatResponse> result = useCase.execute(request);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(aggregateId, response.getCombatId());

                })
                .verifyComplete();


        Mockito.verify(repository).findEventsByAggregateId(aggregateId);
        Mockito.verify(repository, atLeastOnce()).save(any(DomainEvent.class));
    }
}