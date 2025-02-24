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

        // Simular que no hay eventos previos (combate nuevo)
        when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.empty()); // Simular un combate sin eventos previos
        doNothing().when(repository).save(any(DomainEvent.class));

        // Crear la solicitud para finalizar el combate
        EndCombatRequest request = new EndCombatRequest(aggregateId);

        // Act
        Mono<CombatResponse> result = useCase.execute(request);

        // Assert
        StepVerifier.create(result)
                .assertNext(response -> {

                    assertNotNull(response);


                    assertEquals(aggregateId, response.getCombatId());


                    assertNotNull(response.getCharacterTeam());
                    assertEquals(0, response.getCharacterTeam().size()); // Asegurarse de que no hay personajes en el combate

                    assertNotNull(response.getEnemies());
                    assertEquals(0, response.getEnemies().size()); // Asegurarse de que no hay enemigos en el combate
                })
                .verifyComplete();

        // Verificar que los métodos del repositorio se llamaron correctamente
        verify(repository).findEventsByAggregateId(aggregateId);
        verify(repository, atLeastOnce()).save(any(DomainEvent.class));
    }
}