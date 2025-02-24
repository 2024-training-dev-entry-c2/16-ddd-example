package com.zombicide.actions.application.player.addsurvivor;

import com.zombicide.actions.application.shared.repositories.IEventsRepository;
import com.zombicide.actions.domain.player.events.AddedSurvivor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class AddSurvivorUseCaseTest {
  private final AddSurvivorUseCase useCase;
  private final IEventsRepository repository;

  public AddSurvivorUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new AddSurvivorUseCase(repository);
  }

  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new AddedSurvivor("2", "Mariana")
      ));

    AddSurvivorRequest request = new AddSurvivorRequest("aggregatexyz", "1", "Mariana");
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals("Mariana", response.playerName());
        assertEquals(2, response.survivors().size());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }
}