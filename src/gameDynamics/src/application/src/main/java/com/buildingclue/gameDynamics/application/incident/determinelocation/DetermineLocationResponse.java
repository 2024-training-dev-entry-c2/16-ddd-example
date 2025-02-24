package com.buildingclue.gameDynamics.application.incident.determinelocation;

public class DetermineLocationResponse {

  private final String incidentId;
  private final String location;
  private final boolean success;

  public DetermineLocationResponse(String incidentId, String location, boolean success) {
    this.incidentId = incidentId;
    this.location = location;
    this.success = success;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public String getLocation() {
    return location;
  }

  public boolean isSuccess() {
    return success;
  }
}
