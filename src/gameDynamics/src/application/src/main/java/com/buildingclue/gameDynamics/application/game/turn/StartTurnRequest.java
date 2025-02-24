package com.buildingclue.gameDynamics.application.game.turn;

import com.buildingclue.shared.application.Request;

public class StartTurnRequest extends Request {
  private final String playerId;
  private final Integer turnNumber;

  public StartTurnRequest(String aggregateId, String playerId, Integer turnNumber) {
    super(aggregateId);
    this.playerId = playerId;
    this.turnNumber = turnNumber;
  }

  public String getPlayerId() {
    return playerId;
  }

  public Integer getTurnNumber() {
    return turnNumber;
  }
}
