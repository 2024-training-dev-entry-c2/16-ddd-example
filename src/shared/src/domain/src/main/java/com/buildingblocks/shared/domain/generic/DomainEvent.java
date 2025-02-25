package com.buildingblocks.shared.domain.generic;

import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent {
    private Instant when;
    private String uuid;
    private String name;
    private String aggregateRoodId;
    private String aggregateName;
    private Long version;

    protected DomainEvent(String name) {
        this.when = Instant.now();
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.version = 1L;
    }

    public Instant getWhen() {
        return when;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getAggregateRoodId() {
        return aggregateRoodId;
    }

    public void setAggregateRoodId(String aggregateRoodId) {
        this.aggregateRoodId = aggregateRoodId;
    }

    public String getAggregateName() {
        return aggregateName;
    }

    public void setAggregateName(String aggregateName) {
        this.aggregateName = aggregateName;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setWhen(Instant when) {
        this.when = when;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }
}
