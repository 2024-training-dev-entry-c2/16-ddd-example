package com.zombicide.actions.application.action.makeattack;

import com.zombicide.actions.application.shared.action.ActionResponse;
import com.zombicide.actions.application.shared.repositories.IEventsRepository;
import com.zombicide.actions.domain.action.Action;
import com.zombicide.shared.application.ICommandUseCase;
import reactor.core.publisher.Mono;

import static com.zombicide.actions.application.shared.action.ActionMapper.mapToAction;

public class MakeAttackUseCase implements ICommandUseCase<MakeAttackRequest, Mono<ActionResponse>> {
  private final IEventsRepository repository;

  public MakeAttackUseCase(final IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<ActionResponse> execute(MakeAttackRequest request) {
    return repository
      .findEventsByAggregateId(request.getAggregateId())
      .collectList()
      .map(events -> {
        Action action = Action.from(request.getAggregateId(), events);
        action.attack(request.getNameAction(), request.getDescription(), request.getEffect(), request.getPositionX(), request.getPositionY(), request.getNoisy());

        request.getAffects().forEach(affected -> {
          if (affected.typeAffected().equals("Superviviente")) {
            action.injureSurvivor(affected.affectedId(), affected.name(), affected.positionX(), affected.positionY(), affected.damage(), affected.currentState());
          } else {
            action.eliminateZombie(affected.affectedId(), affected.name(), affected.positionX(), affected.positionY(), affected.damage());
          }
        });

        action.getUncommittedEvents().forEach(repository::save);
        action.markEventsAsCommitted();

        return mapToAction(action);
      });
  }
}
