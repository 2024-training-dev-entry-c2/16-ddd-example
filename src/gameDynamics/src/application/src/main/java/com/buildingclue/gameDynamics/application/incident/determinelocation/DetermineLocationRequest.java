package com.buildingclue.gameDynamics.application.incident.determinelocation;

import com.buildingclue.shared.application.Request;

public class DetermineLocationRequest extends Request {

  private final String location;

  public DetermineLocationRequest(String aggregateId, String location) {
    super(aggregateId);
    this.location = location;
  }

  public String getLocation() {
    return location;
  }
}
