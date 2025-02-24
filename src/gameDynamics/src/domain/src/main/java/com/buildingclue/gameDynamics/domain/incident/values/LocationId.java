package com.buildingclue.gameDynamics.domain.incident.values;

import com.buildingclue.shared.domain.generic.Identity;

public class LocationId extends Identity {
  public LocationId() {
    super();
  }

  private LocationId(String value) {
    super(value);
  }

  public static LocationId of(String value) {
    return new LocationId(value);
  }
}
