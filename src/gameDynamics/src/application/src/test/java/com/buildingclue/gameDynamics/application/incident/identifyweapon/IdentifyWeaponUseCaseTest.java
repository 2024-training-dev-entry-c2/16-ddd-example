package com.buildingclue.gameDynamics.application.incident.identifyweapon;

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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IdentifyWeaponUseCaseTest {
  private IEventsRepository eventsRepository;
  private IdentifyWeaponUseCase useCase;

  @BeforeEach
  void setUp() {
    eventsRepository = Mockito.mock(IEventsRepository.class);
    useCase = new IdentifyWeaponUseCase(eventsRepository);
  }

  @Test
  void shouldIdentifyWeaponSuccessfully() {
    String incidentId = "incident-123";
    String weaponName = "Revolver";
    IdentifyWeaponRequest request = new IdentifyWeaponRequest(incidentId, weaponName);

    when(eventsRepository.findEventsByAggregateId(incidentId)).thenReturn(Flux.empty());

    Mono<IdentifyWeaponResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .assertNext(response -> {
              assert response != null;
              assert incidentId.equals(response.getIncidentId());
              assert weaponName.equals(response.getWeaponName());
              assert response.isSuccess();
            })
            .verifyComplete();

    verify(eventsRepository, times(2)).save(any(DomainEvent.class));
  }

  @Test
  void shouldHandleErrorWhenRepositoryFails() {
    String incidentId = "incident-456";
    String weaponName = "Knife";
    IdentifyWeaponRequest request = new IdentifyWeaponRequest(incidentId, weaponName);

    when(eventsRepository.findEventsByAggregateId(incidentId)).thenReturn(Flux.error(new RuntimeException("Database error")));

    Mono<IdentifyWeaponResponse> result = useCase.execute(request);

    StepVerifier.create(result)
            .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                    throwable.getMessage().equals("Database error"))
            .verify();

    verify(eventsRepository, never()).save(any(DomainEvent.class));
  }

}