package com.buildingblocks.shared.application.removeCharacter;

import com.buildingblocks.shared.application.combat.domain.combat.events.CharacterAdded;
import com.buildingblocks.shared.application.combat.removeCharacter.RemoveCharacterRequest;
import com.buildingblocks.shared.application.combat.removeCharacter.RemoveCharacterUseCase;
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

class RemoveCharacterUseCaseTest {

    private IEventsRepository repository;
    private RemoveCharacterUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IEventsRepository.class);
        useCase = new RemoveCharacterUseCase(repository);
    }

    @Test
    void executeSuccess() {
        String aggregateId = "combat123";
        String characterId = "warrior567";

        CharacterAdded warrior = new CharacterAdded(characterId,"warrior", 10, 5);

        Mockito.when(repository.findEventsByAggregateId(aggregateId))
                .thenReturn(Flux.just(warrior));

        RemoveCharacterRequest request = new RemoveCharacterRequest(aggregateId, characterId);

        Mono<CombatResponse> result = useCase.execute(request);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals(aggregateId, response.getCombatId());
                    assertNotNull(response.getCharacterTeam());
                    assertEquals(0, response.getCharacterTeam().size());
                })
                .verifyComplete();

        Mockito.verify(repository).findEventsByAggregateId(aggregateId);
        Mockito.verify(repository, atLeastOnce()).save(any(DomainEvent.class));
    }
}