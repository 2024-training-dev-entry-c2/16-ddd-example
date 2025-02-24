package com.buildingclue.gameDynamics.application.incident.indetifysuspect;

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

class IdentifySuspectUseCaseTest {
  private IEventsRepository eventsRepository;
  private IdentifySuspectUseCase useCase;

  @BeforeEach
  void setUp() {
    eventsRepository = Mockito.mock(IEventsRepository.class);
    useCase = new IdentifySuspectUseCase(eventsRepository);
  }

  @Test
  void shouldIdentifySuspectSuccessfully() {
    String incidentId = "incident-123";
    String suspectName = "John Doe";
    IdentifySuspectRequest request = new IdentifySuspectRequest(incidentId, suspectName);

    when(eventsRepository.findEventsByAggregateId(incidentId)).thenReturn(Flux.empty());

    Mono<IdentifySuspectResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertNotNull(response);
              assertEquals(incidentId, response.getIncidentId());
              assertEquals(suspectName, response.getSuspectName());
              assertTrue(response.isSuccess());
            })
            .verifyComplete();

    verify(eventsRepository, times(2)).save(any(DomainEvent.class));
  }

  @Test
  void shouldHandleErrorWhenRepositoryFails() {
    String incidentId = "incident-456";
    String suspectName = "Jane Doe";
    IdentifySuspectRequest request = new IdentifySuspectRequest(incidentId, suspectName);

    when(eventsRepository.findEventsByAggregateId(incidentId)).thenReturn(Flux.error(new RuntimeException("Database error")));

    Mono<IdentifySuspectResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                    throwable.getMessage().equals("Database error"))
            .verify();

    verify(eventsRepository, never()).save(any(DomainEvent.class));
  }
}