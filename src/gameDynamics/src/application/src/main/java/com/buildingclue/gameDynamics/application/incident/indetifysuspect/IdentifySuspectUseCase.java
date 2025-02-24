package com.buildingclue.gameDynamics.application.incident.indetifysuspect;

import com.buildingclue.gameDynamics.application.game.shared.IEventsRepository;
import com.buildingclue.gameDynamics.domain.incident.Incident;
import com.buildingclue.gameDynamics.domain.incident.entities.Suspect;
import com.buildingclue.gameDynamics.domain.incident.values.IncidentId;
import com.buildingclue.gameDynamics.domain.incident.values.Name;
import com.buildingclue.gameDynamics.domain.incident.values.Occupation;
import com.buildingclue.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class IdentifySuspectUseCase implements ICommandUseCase<IdentifySuspectRequest, Mono<IdentifySuspectResponse>> {
  private final IEventsRepository eventsRepository;

  public IdentifySuspectUseCase(IEventsRepository eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Mono<IdentifySuspectResponse> execute(IdentifySuspectRequest request) {
    return eventsRepository.findEventsByAggregateId(request.getAggregateId())
            .collectList()
            .map(events -> {
              Incident incident = Incident.createWithParams(
                      IncidentId.of(request.getAggregateId()),
                      null, null, null, null,
                      null
              );

              incident.identifySuspect(new Suspect(new Name(request.getSuspectName()), new Occupation("Unknown")));

              incident.getUncommittedEvents().forEach(eventsRepository::save);
              incident.markEventsAsCommitted();

              return new IdentifySuspectResponse(
                      incident.getIdentity().getValue(),
                      request.getSuspectName(),
                      true
              );
            });
  }
}
