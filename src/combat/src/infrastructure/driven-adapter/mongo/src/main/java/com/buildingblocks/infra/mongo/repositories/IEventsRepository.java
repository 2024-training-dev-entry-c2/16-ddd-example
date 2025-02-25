package com.buildingblocks.infra.mongo.repositories;

import com.buildingblocks.infra.mongo.entities.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventsRepository  extends ReactiveMongoRepository<Event, String> {
}
