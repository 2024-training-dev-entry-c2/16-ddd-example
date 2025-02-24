package com.buildingclue.gameDynamics.domain.game.values;

import com.buildingclue.shared.domain.generic.Identity;

public class TurnId extends Identity {
  public TurnId() {
    super();
  }

  private TurnId(String value) {
    super(value);
  }

  public static TurnId of(String value) {
    return new TurnId(value);
  }
}
