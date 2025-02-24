package com.buildingclue.gameDynamics.application.game.move;

import com.buildingclue.shared.application.Request;

public class MoveRequest extends Request {
  private final String playerId;
  private final String fromPosition;
  private final String toPosition;

  public MoveRequest(String aggregateId, String playerId, String fromPosition, String toPosition) {
    super(aggregateId);
    this.playerId = playerId;
    this.fromPosition = fromPosition;
    this.toPosition = toPosition;
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getFromPosition() {
    return fromPosition;
  }

  public String getToPosition() {
    return toPosition;
  }
}
