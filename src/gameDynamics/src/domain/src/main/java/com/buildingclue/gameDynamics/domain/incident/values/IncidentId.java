package com.buildingclue.gameDynamics.domain.incident.values;

import com.buildingclue.shared.domain.generic.Identity;

public class IncidentId extends Identity {
  public IncidentId() {
    super();
  }

  private IncidentId(String value) {
    super(value);
  }

  public static IncidentId of(String value) {
    return new IncidentId(value);
  }
}
