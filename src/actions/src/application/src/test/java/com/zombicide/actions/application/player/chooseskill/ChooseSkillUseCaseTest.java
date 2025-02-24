package com.zombicide.actions.application.player.chooseskill;

import com.zombicide.actions.application.shared.repositories.IEventsRepository;
import com.zombicide.actions.domain.player.events.AddedSurvivor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class ChooseSkillUseCaseTest {
  private final ChooseSkillUseCase useCase;
  private final IEventsRepository repository;

  public ChooseSkillUseCaseTest() {
    repository = Mockito.mock(IEventsRepository.class);
    useCase = new ChooseSkillUseCase(repository);
  }

  @Test
  void executeSuccess() {
    Mockito.when(repository.findEventsByAggregateId(Mockito.anyString()))
      .thenReturn(Flux.just(
        new AddedSurvivor("2", "Mariana")
      ));

    ChooseSkillRequest request = new ChooseSkillRequest("aggregatexyz", "2", "10");
    StepVerifier
      .create(useCase.execute(request))
      .assertNext(response -> {
        assertNotNull(response);
        assertEquals(1, response.survivors().size());
        assertEquals(2, response.survivors().get(0).skills().size());
      })
      .verifyComplete();

    Mockito.verify(repository).findEventsByAggregateId(Mockito.anyString());
  }
}