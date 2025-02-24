package com.buildingclue.gameDynamics.application.incident.solveincident;

import com.buildingclue.gameDynamics.application.game.shared.IEventsRepository;
import com.buildingclue.gameDynamics.domain.incident.Incident;
import com.buildingclue.gameDynamics.domain.incident.values.IncidentId;
import com.buildingclue.gameDynamics.domain.incident.values.StatusCase;
import com.buildingclue.shared.application.ICommandUseCase;
import com.buildingclue.shared.domain.constants.States;
import reactor.core.publisher.Mono;

public class SolveIncidentUseCase implements ICommandUseCase<SolveIncidentRequest, Mono<SolveIncidentResponse>> {
  private final IEventsRepository eventsRepository;

  public SolveIncidentUseCase(IEventsRepository eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Mono<SolveIncidentResponse> execute(SolveIncidentRequest request) {
    return eventsRepository.findEventsByAggregateId(request.getAggregateId())
            .collectList()
            .map(events -> {
              Incident incident = Incident.createWithParams(
                      IncidentId.of(request.getAggregateId()),
                      StatusCase.of(States.OPEN),
                      null, null, null,
                      null
              );


              incident.solveCase();

              incident.getUncommittedEvents().forEach(eventsRepository::save);
              incident.markEventsAsCommitted();

              return new SolveIncidentResponse(
                      incident.getIdentity().getValue(),
                      true
              );
            });
  }
}
