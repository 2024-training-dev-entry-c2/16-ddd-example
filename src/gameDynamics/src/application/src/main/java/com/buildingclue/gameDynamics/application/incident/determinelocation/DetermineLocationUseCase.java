package com.buildingclue.gameDynamics.application.incident.determinelocation;

import com.buildingclue.gameDynamics.application.game.shared.IEventsRepository;
import com.buildingclue.gameDynamics.domain.incident.Incident;
import com.buildingclue.gameDynamics.domain.incident.entities.Location;
import com.buildingclue.gameDynamics.domain.incident.values.Description;
import com.buildingclue.gameDynamics.domain.incident.values.IncidentId;
import com.buildingclue.gameDynamics.domain.incident.values.Name;
import com.buildingclue.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class DetermineLocationUseCase implements ICommandUseCase<DetermineLocationRequest, Mono<DetermineLocationResponse>> {
  private final IEventsRepository eventsRepository;

  public DetermineLocationUseCase(IEventsRepository eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Mono<DetermineLocationResponse> execute(DetermineLocationRequest request) {
    return eventsRepository.findEventsByAggregateId(request.getAggregateId())
            .collectList()
            .map(events -> {
              Incident incident = Incident.createWithParams(
                      IncidentId.of(request.getAggregateId()),
                      null, null, null, null,
                      null
              );

              incident.determineLocation(new Location(
                      new Name(request.getLocation()),
                      new Description("Ubicación determinada automáticamente")
              ));

              incident.getUncommittedEvents().forEach(eventsRepository::save);
              incident.markEventsAsCommitted();

              return new DetermineLocationResponse(
                      incident.getIdentity().getValue(),
                      request.getLocation(),
                      true
              );
            });
  }
}
