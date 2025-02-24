package com.buildingclue.gameDynamics.application.game.startgame;

public class StartGameResponse {
  private final String gameId;
  private final String playerId;
  private final String gameState;

  public StartGameResponse(String gameId, String playerId, String gameState) {
    this.gameId = gameId;
    this.playerId = playerId;
    this.gameState = gameState;
  }

  public String getGameId() {
    return gameId;
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getGameState() {
    return gameState;
  }
}
