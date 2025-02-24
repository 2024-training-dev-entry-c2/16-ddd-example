package com.zombicide.actions.application.player.discardweapon;

import com.zombicide.actions.application.shared.repositories.IEventsRepository;
import com.zombicide.actions.domain.player.events.AddedSurvivor;
import com.zombicide.actions.domain.player.events.ChosenSkill;
import com.zombicide.actions.domain.player.events.ObtainedWeapon;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class DiscardWeaponUseCaseTest {
  private final DiscardWeaponUseCase useCase;
  private final IEventsRepository repository;

  public DiscardWeaponUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new DiscardWeaponUseCase(repository);
  }

  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new AddedSurvivor("2", "Mariana"),
        new ChosenSkill("2", "10"),
        new ObtainedWeapon("2")
      ));

    DiscardWeaponRequest request = new DiscardWeaponRequest("aggregatexyz", "200", "2");
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(1, response.survivors().size());
        assertEquals(1, response.survivors().get(0).weapons().size());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }
}