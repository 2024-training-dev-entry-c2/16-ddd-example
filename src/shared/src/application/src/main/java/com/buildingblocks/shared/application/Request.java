package com.buildingblocks.shared.application;

public abstract class Request {

    private final String aggregateId;

    public Request(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getAggregateId() {
        return aggregateId;
    }

}
