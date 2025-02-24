package com.buildingclue.gameDynamics.domain.game.values;

import com.buildingclue.shared.domain.generic.Identity;

public class BoardId extends Identity {
  public BoardId() {
    super();
  }

  private BoardId(String value) {
    super(value);
  }

  public static BoardId of(String value) {
    return new BoardId(value);
  }
}
