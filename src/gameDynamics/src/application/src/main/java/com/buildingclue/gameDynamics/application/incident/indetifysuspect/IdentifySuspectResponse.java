package com.buildingclue.gameDynamics.application.incident.indetifysuspect;

public class IdentifySuspectResponse {
  private final String incidentId;
  private final String suspectName;
  private final boolean success;

  public IdentifySuspectResponse(String incidentId, String suspectName, boolean success) {
    this.incidentId = incidentId;
    this.suspectName = suspectName;
    this.success = success;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public String getSuspectName() {
    return suspectName;
  }

  public boolean isSuccess() {
    return success;
  }
}
