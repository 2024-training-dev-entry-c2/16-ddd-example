package com.buildingblocks.industries.application.shared.ports;

import com.buildingblocks.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface IEventRepositoryPort {
    Flux<DomainEvent> findAllAggregates();
    Flux<DomainEvent> findEventsByAggregateId(String aggregateId);
    void save(DomainEvent domainEvent);
}
