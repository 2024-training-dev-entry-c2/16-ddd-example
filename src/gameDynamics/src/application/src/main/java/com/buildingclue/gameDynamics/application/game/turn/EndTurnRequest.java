package com.buildingclue.gameDynamics.application.game.turn;

import com.buildingclue.shared.application.Request;

public class EndTurnRequest extends Request {
  private final String playerId;
  private final Integer turnNumber;
  private final String reason;

  public EndTurnRequest(String aggregateId, String playerId, Integer turnNumber, String reason) {
    super(aggregateId);
    this.playerId = playerId;
    this.turnNumber = turnNumber;
    this.reason = reason;
  }

  public String getPlayerId() {
    return playerId;
  }

  public Integer getTurnNumber() {
    return turnNumber;
  }

  public String getReason() {
    return reason;
  }
}
