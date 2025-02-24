package com.buildingclue.gameDynamics.domain.game.values;

import com.buildingclue.shared.domain.generic.Identity;

public class RuleId extends Identity {
  public RuleId() {
    super();
  }

  private RuleId(String value) {
    super(value);
  }

  public static RuleId of(String value) {
    return new RuleId(value);
  }
}
