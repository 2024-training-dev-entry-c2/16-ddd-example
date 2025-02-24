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

        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.empty());
        doNothing().when(repository).save(any(DomainEvent.class));

        AddCharacterRequest request = new AddCharacterRequest(aggregateId, characterId, characterName, characterHeal, characterInitiative);

        Mono<CombatResponse> result = useCase.execute(request);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(aggregateId, response.getCombatId());
                    assertNotNull(response.getCharacterTeam());
                    assertEquals(1, response.getCharacterTeam().size());

                    CombatResponse.CharacterDetails characterDetails = response.getCharacterTeam().get(0);
                    assertEquals(characterId, characterDetails.getCharacterId());
                    assertEquals(characterName, characterDetails.getName());
                    assertEquals(characterHeal, characterDetails.getHealth());
                    assertEquals(characterInitiative, characterDetails.getInitiative());
                })
                .verifyComplete();

        Mockito.verify(repository).findEventsByAggregateId(aggregateId);
        Mockito.verify(repository, atLeastOnce()).save(any(DomainEvent.class));
    }

}