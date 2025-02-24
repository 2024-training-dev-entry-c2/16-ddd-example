package com.buildingclue.gameDynamics.domain.game.values;

import com.buildingclue.shared.domain.generic.Identity;

public class GameId extends Identity {
  public GameId() {
    super();
  }

  private GameId(String value) {
    super(value);
  }

  public static GameId of(String value) {
    return new GameId(value);
  }
}
