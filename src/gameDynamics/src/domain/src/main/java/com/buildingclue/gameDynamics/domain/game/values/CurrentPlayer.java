package com.buildingclue.gameDynamics.domain.game.values;

import com.buildingclue.shared.domain.generic.IValueObject;

import static com.buildingclue.shared.domain.utils.ValidationUtils.validateString;

public class CurrentPlayer implements IValueObject {

  private final String playerId;

  private CurrentPlayer(String playerId) {
    this.playerId = playerId;
    validate();
  }

  public static CurrentPlayer of(String playerId) {
    return new CurrentPlayer(playerId);
  }

  @Override
  public void validate() {
    validateString(playerId, "playerId cannot be null or empty");
  }

  public String getPlayerId() {
    return playerId;
  }
}
