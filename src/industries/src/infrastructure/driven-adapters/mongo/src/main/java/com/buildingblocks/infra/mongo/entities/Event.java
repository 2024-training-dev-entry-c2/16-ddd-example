package com.buildingblocks.infra.mongo.entities;

import com.buildingblocks.shared.domain.generic.DomainEvent;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "events")
public class Event {
    private DomainEvent domainEvent;

    public Event(DomainEvent domainEvent) {
        this.domainEvent = domainEvent;
    }

    public Event() {
    }

    public DomainEvent getDomainEvent() {
        return domainEvent;
    }

    public void setDomainEvent(DomainEvent domainEvent) {
        this.domainEvent = domainEvent;
    }
}
