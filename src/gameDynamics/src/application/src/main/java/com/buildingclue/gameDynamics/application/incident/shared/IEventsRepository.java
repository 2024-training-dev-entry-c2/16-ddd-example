package com.buildingclue.gameDynamics.application.incident.shared;

import com.buildingclue.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface IEventsRepository {
  Flux<DomainEvent> findEventsByAggregateId(String aggregateId);
  void save(DomainEvent event);
}
