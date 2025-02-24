package com.buildingclue.gameDynamics.domain.incident.values;

import com.buildingclue.shared.domain.generic.Identity;

public class SuspectId extends Identity {
  public SuspectId() {
    super();
  }

  private SuspectId(String value) {
    super(value);
  }

  public static SuspectId of(String value) {
    return new SuspectId(value);
  }
}
