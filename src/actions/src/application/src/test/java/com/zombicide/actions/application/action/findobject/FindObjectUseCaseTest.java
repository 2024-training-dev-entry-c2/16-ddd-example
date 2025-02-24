package com.zombicide.actions.application.action.findobject;

import com.zombicide.actions.application.shared.repositories.IEventsRepository;
import com.zombicide.actions.domain.action.events.FoundObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FindObjectUseCaseTest {
  private final FindObjectUseCase useCase;
  private final IEventsRepository repository;

  public FindObjectUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new FindObjectUseCase(repository);
  }

  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new FoundObject( 2, 4, false)
      ));

    FindObjectRequest request = new FindObjectRequest("aggregatexyz", 2, 2, false);
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(2, response.type().positionX());
        assertEquals(2, response.type().positionY());
        assertEquals(0, response.type().amountNoise());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }
}