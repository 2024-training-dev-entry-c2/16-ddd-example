package com.zombicide.actions.application.action.opendoor;

import com.zombicide.actions.application.shared.repositories.IEventsRepository;
import com.zombicide.actions.domain.action.events.FoundObject;
import com.zombicide.actions.domain.action.events.OpenedDoor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class OpenDoorUseCaseTest {
  private final OpenDoorUseCase useCase;
  private final IEventsRepository repository;

  public OpenDoorUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new OpenDoorUseCase(repository);
  }

  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new FoundObject(2, 4, false)
      ));

    OpenDoorRequest request = new OpenDoorRequest("aggregatexyz", 3, 6, true);
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(3, response.type().positionX());
        assertEquals(6, response.type().positionY());
        assertEquals(1, response.type().amountNoise());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }
}