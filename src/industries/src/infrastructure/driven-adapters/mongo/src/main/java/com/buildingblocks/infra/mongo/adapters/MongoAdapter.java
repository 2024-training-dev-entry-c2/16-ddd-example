package com.buildingblocks.infra.mongo.adapters;

import com.buildingblocks.industries.application.shared.ports.IEventRepositoryPort;
import com.buildingblocks.infra.mongo.entities.Event;
import com.buildingblocks.infra.mongo.repositories.IEventsRepository;
import com.buildingblocks.shared.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

public class MongoAdapter implements IEventRepositoryPort{
    private final IEventsRepository eventsRepository;

    public MongoAdapter(IEventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public Flux<DomainEvent> findAllAggregates() {
        return eventsRepository.findAll().map(Event::getDomainEvent);
    }

    @Override
    public Flux<DomainEvent> findEventsByAggregateId(String aggregateId) {
        return findAllAggregates().filter(event -> event.getAggregateRoodId().equals(aggregateId));
    }

    @Override
    public void save(DomainEvent domainEvent) {
        eventsRepository.save(new Event(domainEvent));
    }
}
