package com.zombicide.actions.application.makemovement;

import com.zombicide.actions.application.shared.repositories.IEventsRepository;
import com.zombicide.actions.domain.action.Action;
import com.zombicide.actions.domain.player.Player;
import com.zombicide.shared.application.ICommandUseCase;
import com.zombicide.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;

public class MakeMovementUseCase implements ICommandUseCase<MakeMovementRequest, Mono<MakeMovementResponse>> {
  private final IEventsRepository repository;

  public MakeMovementUseCase(final IEventsRepository repository) {
    this.repository = repository;
  }

  @Override
  public Mono<MakeMovementResponse> execute(MakeMovementRequest request) {
    Mono<List<DomainEvent>> actionEvents = repository.findEventsByAggregateId(request.getAggregateId()).collectList();
    Mono<List<DomainEvent>> playerEvents = repository.findEventsByAggregateId(request.getAggregatePlayerId()).collectList();

    Mono<Tuple2<List<DomainEvent>, List<DomainEvent>>> events = Mono.zip(actionEvents, playerEvents);

    return events
      .map(tuple -> {
        Action action = Action.from(request.getAggregateId(), tuple.getT1());
        action.move(request.getPositionX(), request.getPositionY(), request.getNoisy());

        action.getUncommittedEvents().forEach(repository::save);
        action.markEventsAsCommitted();

        Player player = Player.from(request.getAggregatePlayerId(), tuple.getT2());
        player.changeSurvivorPosition(request.getSurvivorId(), request.getPositionX(), request.getPositionY());

        player.getUncommittedEvents().forEach(repository::save);
        player.markEventsAsCommitted();

        return MakeMovementMapper.mapToMakeMovement(action, player);
      });
  }
}
