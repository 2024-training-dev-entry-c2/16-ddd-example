package com.buildingblocks.infra.mongo.repositories;

import com.buildingblocks.infra.mongo.entities.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IEventsRepository extends ReactiveMongoRepository<Event, String> {
}
