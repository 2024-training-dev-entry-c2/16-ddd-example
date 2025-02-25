package com.buildingblocks.shared.application.shared.ports;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface IEventsRepositoryPort {
    Flux<DomainEvent> findAll();
    Flux<DomainEvent> findEventsByAggregateId(String aggregateId);
    void save(DomainEvent domainEvent);
}
