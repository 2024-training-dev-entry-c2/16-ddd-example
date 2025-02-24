package com.buildingclue.gameDynamics.application.incident.indetifysuspect;

import com.buildingclue.shared.application.Request;

public class IdentifySuspectRequest extends Request {

  private final String suspectName;

  protected IdentifySuspectRequest(String aggregateId, String suspectName) {
    super(aggregateId);
    this.suspectName = suspectName;
  }

  public String getSuspectName() {
    return suspectName;
  }
}
