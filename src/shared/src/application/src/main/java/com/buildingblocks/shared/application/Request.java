package com.buildingblocks.shared.application;

public abstract class Request {
<<<<<<< HEAD
    private final String aggregateId;

    public Request(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getAggregateId() {
        return aggregateId;
    }
=======
  private final String aggregateId;

  protected Request(String aggregateId) {
    this.aggregateId = aggregateId;
  }

  public String getAggregateId() {
    return aggregateId;
  }
>>>>>>> d99e95c55ec8ff4b3ec7bee85519df2683c5e43a
}
