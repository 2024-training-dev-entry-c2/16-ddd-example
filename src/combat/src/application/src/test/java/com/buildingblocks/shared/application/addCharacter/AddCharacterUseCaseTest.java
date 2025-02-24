package com.buildingblocks.shared.application.addCharacter;

import com.buildingblocks.shared.application.combat.addCharacter.AddCharacterRequest;
import com.buildingblocks.shared.application.combat.addCharacter.AddCharacterUseCase;
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

class AddCharacterUseCaseTest {
    private IEventsRepository repository;
    private AddCharacterUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new AddCharacterUseCase(repository);
    }
    @Test
    void executeSuccess() {
        // Arrange
        String aggregateId = "combat123";
        String characterId = "character567";
        String characterName = "Aragorn";
        Integer characterHeal = 20;
        Integer characterInitiative = 7;

        // Simular que no hay eventos previos (combate nuevo)
        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.empty()); // Simular un combate sin eventos previos
        doNothing().when(repository).save(any(DomainEvent.class));

        // Crear la solicitud para agregar un personaje
        AddCharacterRequest request = new AddCharacterRequest(aggregateId, characterId, characterName, characterHeal, characterInitiative);

        // Act
        Mono<CombatResponse> result = useCase.execute(request);

        // Assert
        StepVerifier.create(result)
                .assertNext(response -> {
                    // Verificar que la respuesta no es nula
                    assertNotNull(response);

                    // Verificar que el combatId es correcto
                    assertEquals(aggregateId, response.getCombatId());

                    // Verificar que el personaje fue agregado correctamente
                    assertNotNull(response.getCharacterTeam());
                    assertEquals(1, response.getCharacterTeam().size()); // Asegurarse de que hay un personaje en el combate

                    // Verificar los detalles del personaje agregado
                    CombatResponse.CharacterDetails characterDetails = response.getCharacterTeam().get(0);
                    assertEquals(characterId, characterDetails.getCharacterId());
                    assertEquals(characterName, characterDetails.getName());
                    assertEquals(characterHeal, characterDetails.getHealth());
                    assertEquals(characterInitiative, characterDetails.getInitiative());
                })
                .verifyComplete();

        // Verificar que los métodos del repositorio se llamaron correctamente
        Mockito.verify(repository).findEventsByAggregateId(aggregateId);
        Mockito.verify(repository, atLeastOnce()).save(any(DomainEvent.class));
    }

}