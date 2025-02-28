package com.buildingblocks.infra.mongo.repositories;

import com.buildingblocks.infra.mongo.entities.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IEventsRepository  extends ReactiveMongoRepository<Event, String> {
    Mono<Event> findByDomainEventUuid(String uuid);
}
