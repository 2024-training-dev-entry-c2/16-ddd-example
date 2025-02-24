package com.zombicide.actions.application.action.makeattack;

import com.zombicide.actions.application.shared.repositories.IEventsRepository;
import com.zombicide.actions.domain.action.events.FoundObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MakeAttackUseCaseTest {
  private final MakeAttackUseCase useCase;
  private final IEventsRepository repository;

  public MakeAttackUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new MakeAttackUseCase(repository);
  }

  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new FoundObject(2, 4, false)
      ));

    MakeAttackRequest request = new MakeAttackRequest("aggregatexyz", 3, 6, true, List.of(new MakeAttackRequest.Affected("1", "Superviviente", "Mariana", 2, 4, 1, "Herido")));
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(3, response.type().positionX());
        assertEquals(6, response.type().positionY());
        assertEquals(1, response.affects().size());
        assertEquals("Superviviente", response.affects().get(0).typeAffected());
        assertEquals("Herido", response.affects().get(0).currentState());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }

  @Test
  void executeSuccessWithTwoAffects() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new FoundObject(2, 4, false)
      ));

    MakeAttackRequest request = new MakeAttackRequest("aggregatexyz", 3, 6, true, List.of(new MakeAttackRequest.Affected("1", "Superviviente", "John", 1, 4, 1, "Herido"), new MakeAttackRequest.Affected("2", "Zombie", "Zombie Corredor", 2, 4, 1, "Muerto")));
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(3, response.type().positionX());
        assertEquals(6, response.type().positionY());
        assertEquals(2, response.affects().size());
        assertEquals("Superviviente", response.affects().get(0).typeAffected());
        assertEquals("Herido", response.affects().get(0).currentState());
        assertEquals("Zombie", response.affects().get(1).typeAffected());
        assertEquals("Muerto", response.affects().get(1).currentState());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }
}