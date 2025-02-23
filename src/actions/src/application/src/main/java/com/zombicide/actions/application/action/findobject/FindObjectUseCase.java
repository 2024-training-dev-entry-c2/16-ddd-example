package com.zombicide.actions.application.action.findobject;

import com.zombicide.actions.application.shared.action.ActionResponse;
import com.zombicide.actions.application.shared.repositories.IEventsRepository;
import com.zombicide.actions.domain.action.Action;
import com.zombicide.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.zombicide.actions.application.shared.action.ActionMapper.mapToAction;

public class FindObjectUseCase implements ICommandUseCase<FindObjectRequest, Mono<ActionResponse>> {
  private final IEventsRepository repository;

  public FindObjectUseCase(final IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<ActionResponse> execute(FindObjectRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events -> {
        Action action = Action.from(request.getAggregateId(), events);
        action.findObject(request.getNameAction(), request.getDescription(), request.getEffect(), request.getPositionX(), request.getPositionY(), request.getNoisy());

        action.getUncommittedEvents().forEach(repository::save);
        action.markEventsAsCommitted();

        return mapToAction(action);
      });
  }
}
