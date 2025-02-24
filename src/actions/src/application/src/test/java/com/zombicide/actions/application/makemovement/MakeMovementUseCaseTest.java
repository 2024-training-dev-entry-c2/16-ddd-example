package com.zombicide.actions.application.makemovement;

import com.zombicide.actions.application.shared.repositories.IEventsRepository;
import com.zombicide.actions.domain.action.events.FoundObject;
import com.zombicide.actions.domain.player.events.AddedSurvivor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class MakeMovementUseCaseTest {
  private final MakeMovementUseCase useCase;
  private final IEventsRepository repository;

  public MakeMovementUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new MakeMovementUseCase(repository);
  }

  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new AddedSurvivor("2", "Mariana"),
        new FoundObject(2, 4, false)
      ));

    MakeMovementRequest request = new MakeMovementRequest("aggregatexyz", 2, 4, true, "1", "2");
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(2, response.actionResponse().type().positionX());
        assertEquals(4, response.actionResponse().type().positionY());
        assertEquals(1, response.actionResponse().type().amountNoise());
        assertEquals(2, response.playerResponse().survivors().get(0).positionX());
        assertEquals(4, response.playerResponse().survivors().get(0).positionY());
      })
      .verifyComplete();

    Mockito.verify(repository, times(2)).findEventsByAggregateId(Mockito.anyString());
  }
}