package com.zombicide.actions.application.action.opendoor;

import com.zombicide.actions.application.shared.action.ActionResponse;
import com.zombicide.actions.application.shared.repositories.IEventsRepository;
import com.zombicide.actions.domain.action.Action;
import com.zombicide.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.zombicide.actions.application.shared.action.ActionMapper.mapToAction;

public class OpenDoorUseCase implements ICommandUseCase<OpenDoorRequest, Mono<ActionResponse>> {
  private final IEventsRepository repository;

  public OpenDoorUseCase(final IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<ActionResponse> execute(OpenDoorRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events -> {
        Action action = Action.from(request.getAggregateId(), events);
        action.openDoor(request.getPositionX(), request.getPositionY(), request.getNoisy());

        action.getUncommittedEvents();
        action.markEventsAsCommitted();

        return mapToAction(action);
      });
  }
}
