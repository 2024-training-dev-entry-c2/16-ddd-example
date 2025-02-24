package com.zombicide.actions.application.player.obtainweapon;

import com.zombicide.actions.application.shared.repositories.IEventsRepository;
import com.zombicide.actions.domain.player.events.AddedSurvivor;
import com.zombicide.actions.domain.player.events.ChosenSkill;
import com.zombicide.actions.domain.player.events.ObtainedWeapon;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class ObtainWeaponUseCaseTest {
  private final ObtainWeaponUseCase useCase;
  private final IEventsRepository repository;

  public ObtainWeaponUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new ObtainWeaponUseCase(repository);
  }

  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new AddedSurvivor("2", "Mariana"),
        new AddedSurvivor("3", "Mariana"),
        new ChosenSkill("2", "10")
      ));

    ObtainWaponRequest request = new ObtainWaponRequest("aggregatexyz", "3");
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(2, response.survivors().size());
        assertEquals(2, response.survivors().get(1).weapons().size());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }
}