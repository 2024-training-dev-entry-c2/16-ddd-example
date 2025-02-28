package com.buildingblocks.infra.mongo.adapters;

import com.buildingblocks.infra.mongo.entities.Event;
import com.buildingblocks.infra.mongo.repositories.IEventsRepository;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;
import com.buildingblocks.shared.application.shared.ports.IEventsRepositoryPort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MongoAdapter implements IEventsRepositoryPort {
    private final IEventsRepository eventRepository;

    public MongoAdapter(IEventsRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Flux<DomainEvent> findAll() {
        return eventRepository.findAll()
                .map(Event::getDomainEvent)
                .doOnNext(event -> System.out.println("Retrieved event: " + event));
    }


    @Override
    public Flux<DomainEvent> findEventsByAggregateId(String aggregateId) {
        return eventRepository.findAll()
                .map(Event::getDomainEvent)
                .filter(event -> aggregateId.equals(event.getAggregateRootId()))
                .doOnNext(event -> System.out.println("Filtered event for aggregate " + aggregateId + ": " + event));
    }


    @Override
    public void save(DomainEvent domainEvent) {
        eventRepository
                .findByDomainEventUuid(domainEvent.getUuid())
                .hasElement()
                .flatMap(exists -> {
                    if (!exists) {
                        return eventRepository.save(new Event(domainEvent))
                                .doOnSuccess(event -> System.out.println("Saved event: " + event));
                    } else {
                        System.out.println("Event already exists: " + domainEvent.getUuid());
                        return Mono.empty();
                    }
                })
                .subscribe();
    }
}
