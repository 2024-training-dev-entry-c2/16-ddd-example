package com.buildingclue.gameDynamics.application.game.turn;

public class EndTurnResponse {
  private final String gameId;
  private final String playerId;
  private final Integer turnNumber;
  private final String reason;
  private final Boolean isTurnEnded;

  public EndTurnResponse(String gameId, String playerId, Integer turnNumber, String reason, Boolean isTurnEnded) {
    this.gameId = gameId;
    this.playerId = playerId;
    this.turnNumber = turnNumber;
    this.reason = reason;
    this.isTurnEnded = isTurnEnded;
  }

  public String getGameId() {
    return gameId;
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

  public Boolean getIsTurnEnded() {
    return isTurnEnded;
  }
}
