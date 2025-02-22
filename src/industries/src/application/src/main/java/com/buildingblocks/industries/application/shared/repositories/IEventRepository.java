package com.buildingblocks.industries.application.shared.repositories;

import com.buildingblocks.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface IEventRepository {
    Flux<DomainEvent> findEventsByAggregateId(String aggregateId);
    void save(DomainEvent domainEvent);
}
