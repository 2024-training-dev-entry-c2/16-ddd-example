package com.buildingclue.gameDynamics.application.game.turn;

public class StartTurnResponse {
  private final String gameId;
  private final String playerId;
  private final Integer turnNumber;
  private final Boolean isTurnStarted;

  public StartTurnResponse(String gameId, String playerId, Integer turnNumber, Boolean isTurnStarted) {
    this.gameId = gameId;
    this.playerId = playerId;
    this.turnNumber = turnNumber;
    this.isTurnStarted = isTurnStarted;
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

  public Boolean getIsTurnStarted() {
    return isTurnStarted;
  }
}
