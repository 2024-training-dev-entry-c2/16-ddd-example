package com.buildingblocks.appointments.application.shared.repositories;

import com.buildingblocks.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface IEventsRepository {
  Flux<DomainEvent> findAllAggregates();
  Flux<DomainEvent> findEventsByAggregateId(String aggregateId);
  void save(DomainEvent domainEvent);
}
