package com.buildingclue.gameDynamics.application.game.move;

public class MoveResponse {
  private final String gameId;
  private final String playerId;
  private final String fromPosition;
  private final String toPosition;
  private final Boolean isMoveValid;

  public MoveResponse(String gameId, String playerId, String fromPosition, String toPosition, Boolean isMoveValid) {
    this.gameId = gameId;
    this.playerId = playerId;
    this.fromPosition = fromPosition;
    this.toPosition = toPosition;
    this.isMoveValid = isMoveValid;
  }

  public String getGameId() {
    return gameId;
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

  public Boolean getIsMoveValid() {
    return isMoveValid;
  }
}
