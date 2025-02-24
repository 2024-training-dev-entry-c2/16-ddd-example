package com.buildingclue.gameDynamics.application.incident.solveincident;

import com.buildingclue.gameDynamics.application.game.shared.IEventsRepository;
import com.buildingclue.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class SolveIncidentUseCaseTest {
  private IEventsRepository eventsRepository;
  private SolveIncidentUseCase useCase;

  @BeforeEach
  void setUp() {
    eventsRepository = Mockito.mock(IEventsRepository.class);
    useCase = new SolveIncidentUseCase(eventsRepository);
  }

  @Test
  void shouldSolveIncidentSuccessfully() {
    String incidentId = "incident-123";
    SolveIncidentRequest request = new SolveIncidentRequest(incidentId);

    when(eventsRepository.findEventsByAggregateId(incidentId)).thenReturn(Flux.empty());

    Mono<SolveIncidentResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertNotNull(response);
              assertEquals(incidentId, response.getIncidentId());
              assertTrue(response.isSuccess());
            })
            .verifyComplete();

    verify(eventsRepository, times(2)).save(any(DomainEvent.class));
  }

  @Test
  void shouldHandleErrorWhenRepositoryFails() {
    String incidentId = "incident-456";
    SolveIncidentRequest request = new SolveIncidentRequest(incidentId);

    when(eventsRepository.findEventsByAggregateId(incidentId)).thenReturn(Flux.error(new RuntimeException("Database error")));

    Mono<SolveIncidentResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                    throwable.getMessage().equals("Database error"))
            .verify();

    verify(eventsRepository, never()).save(any(DomainEvent.class));
  }
}