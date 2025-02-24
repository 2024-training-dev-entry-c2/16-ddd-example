package com.buildingblocks.appointments.infra.mongo.repositories;

import com.buildingblocks.appointments.infra.mongo.entities.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IEventsRepository extends ReactiveMongoRepository<Event, String> {
}
