package com.buildingclue.gameDynamics.application.incident.solveincident;

public class SolveIncidentResponse {
  private final String incidentId;
  private final boolean success;

  public SolveIncidentResponse(String incidentId, boolean success) {
    this.incidentId = incidentId;
    this.success = success;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public boolean isSuccess() {
    return success;
  }
}
