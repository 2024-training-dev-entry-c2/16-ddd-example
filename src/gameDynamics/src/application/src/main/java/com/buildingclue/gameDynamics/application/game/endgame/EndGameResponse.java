package com.buildingclue.gameDynamics.application.game.endgame;

public class EndGameResponse {
  private final String gameId;
  private final String winnerPlayerId;
  private final Boolean wasCaseSolved;
  private final String gameState;

  public EndGameResponse(String gameId, String winnerPlayerId, Boolean wasCaseSolved, String gameState) {
    this.gameId = gameId;
    this.winnerPlayerId = winnerPlayerId;
    this.wasCaseSolved = wasCaseSolved;
    this.gameState = gameState;
  }

  public String getGameId() {
    return gameId;
  }

  public String getWinnerPlayerId() {
    return winnerPlayerId;
  }

  public Boolean getWasCaseSolved() {
    return wasCaseSolved;
  }

  public String getGameState() {
    return gameState;
  }
}
