package com.buildingblocks.shared.application.shared;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public interface IEventsRepository {
    Flux<DomainEvent> findEventsByAggregateId(String aggregateId);
    void save(DomainEvent domainEvent);
}
