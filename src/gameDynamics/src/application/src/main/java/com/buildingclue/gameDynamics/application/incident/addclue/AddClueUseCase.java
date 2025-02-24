package com.buildingclue.gameDynamics.application.incident.addclue;

import com.buildingclue.gameDynamics.application.game.shared.IEventsRepository;
import com.buildingclue.gameDynamics.domain.incident.Incident;
import com.buildingclue.gameDynamics.domain.incident.values.Clue;
import com.buildingclue.gameDynamics.domain.incident.values.IncidentId;
import com.buildingclue.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

public class AddClueUseCase implements ICommandUseCase<AddClueRequest, Mono<AddClueResponse>> {
  private final IEventsRepository eventsRepository;

  public AddClueUseCase(IEventsRepository eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @Override
  public Mono<AddClueResponse> execute(AddClueRequest request) {
    return eventsRepository.findEventsByAggregateId(request.getAggregateId())
            .collectList()
            .map(events -> {
              Incident incident = Incident.createWithParams(
                      IncidentId.of(request.getAggregateId()),
                      null, null, null, null,
                      null
              );

              incident.addClue(new Clue(request.getClue()));

              incident.getUncommittedEvents().forEach(eventsRepository::save);
              incident.markEventsAsCommitted();

              return new AddClueResponse(
                      incident.getIdentity().getValue(),
                      request.getClue(),
                      true
              );
            });
  }
}
