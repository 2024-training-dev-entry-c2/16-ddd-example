package com.buildingclue.gameDynamics.application.incident.determinelocation;

import com.buildingclue.gameDynamics.application.game.shared.IEventsRepository;
import com.buildingclue.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DetermineLocationUseCaseTest {
  private IEventsRepository eventsRepository;
  private DetermineLocationUseCase useCase;

  @BeforeEach
  void setUp() {
    eventsRepository = Mockito.mock(IEventsRepository.class);
    useCase = new DetermineLocationUseCase(eventsRepository);
  }

  @Test
  void shouldDetermineLocationSuccessfully() {
    String incidentId = "incident-123";
    String locationName = "Mansion Haunted";
    DetermineLocationRequest request = new DetermineLocationRequest(incidentId, locationName);

    when(eventsRepository.findEventsByAggregateId(incidentId)).thenReturn(Flux.empty());
    doNothing().when(eventsRepository).save(any(DomainEvent.class));

    Mono<DetermineLocationResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assertEquals(incidentId, response.getIncidentId());
              assertEquals(locationName, response.getLocation());
              assertEquals(true, response.isSuccess());
            })
            .verifyComplete();

    verify(eventsRepository, atLeastOnce()).save(any(DomainEvent.class));
  }

  @Test
  void shouldHandleErrorWhenRepositoryFails() {
    String incidentId = "incident-456";
    String locationName = "Secret Basement";
    DetermineLocationRequest request = new DetermineLocationRequest(incidentId, locationName);

    when(eventsRepository.findEventsByAggregateId(incidentId)).thenReturn(Flux.error(new RuntimeException("Database error")));

    Mono<DetermineLocationResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                    throwable.getMessage().equals("Database error"))
            .verify();

    verify(eventsRepository, never()).save(any(DomainEvent.class));
  }

}