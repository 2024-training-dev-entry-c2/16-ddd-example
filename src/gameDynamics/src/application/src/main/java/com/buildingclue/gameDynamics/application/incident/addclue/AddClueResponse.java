package com.buildingclue.gameDynamics.application.incident.addclue;

public class AddClueResponse {
  private final String incidentId;
  private final String clue;
  private final boolean success;

  public AddClueResponse(String incidentId, String clue, boolean success) {
    this.incidentId = incidentId;
    this.clue = clue;
    this.success = success;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public String getClue() {
    return clue;
  }

  public boolean isSuccess() {
    return success;
  }
}
