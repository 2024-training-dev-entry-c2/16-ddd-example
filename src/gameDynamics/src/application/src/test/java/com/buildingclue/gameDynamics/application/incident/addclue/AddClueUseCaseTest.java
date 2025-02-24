package com.buildingclue.gameDynamics.application.incident.addclue;

import com.buildingclue.gameDynamics.application.game.shared.IEventsRepository;
import com.buildingclue.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddClueUseCaseTest {
  private IEventsRepository eventsRepository;
  private AddClueUseCase useCase;

  @BeforeEach
  void setUp() {
    eventsRepository = Mockito.mock(IEventsRepository.class);
    useCase = new AddClueUseCase(eventsRepository);
  }

  @Test
  void shouldAddClueSuccessfully() {
    String incidentId = "incident-123";
    String clueText = "Bloody Knife";
    AddClueRequest request = new AddClueRequest(incidentId, clueText);

    when(eventsRepository.findEventsByAggregateId(incidentId)).thenReturn(Flux.empty());

    Mono<AddClueResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertNotNull(response);
              assertEquals(incidentId, response.getIncidentId());
              assertEquals(clueText, response.getClue());
              assertTrue(response.isSuccess());
            })
            .verifyComplete();

    verify(eventsRepository, atLeastOnce()).save(any(DomainEvent.class));
  }


  @Test
  void shouldHandleErrorWhenRepositoryFails() {
    String incidentId = "incident-456";
    String clueText = "Fingerprint on Gun";
    AddClueRequest request = new AddClueRequest(incidentId, clueText);

    when(eventsRepository.findEventsByAggregateId(incidentId)).thenReturn(Flux.error(new RuntimeException("Database error")));

    Mono<AddClueResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                    throwable.getMessage().equals("Database error"))
            .verify();

    verify(eventsRepository, never()).save(any(DomainEvent.class));
  }
}