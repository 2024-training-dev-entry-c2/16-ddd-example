package com.buildingclue.gameDynamics.domain.game.values;

import com.buildingclue.shared.domain.generic.Identity;

public class PlayerId extends Identity {
  public PlayerId() {
    super();
  }

  private PlayerId(String value) {
    super(value);
  }

  public static PlayerId of(String value) {
    return new PlayerId(value);
  }
}
