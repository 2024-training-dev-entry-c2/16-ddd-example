package com.buildingclue.gameDynamics.application.game.shared;

import com.buildingclue.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface IEventsRepository {
  Flux<DomainEvent> findEventsByAggregateId(String aggregateId);
  void save(DomainEvent event);
}
