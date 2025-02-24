package com.buildingclue.gameDynamics.domain.game.entities;

import com.buildingclue.gameDynamics.domain.game.values.CurrentPlayer;
import com.buildingclue.gameDynamics.domain.game.values.GameState;
import com.buildingclue.gameDynamics.domain.game.values.PlayerId;
import com.buildingclue.gameDynamics.domain.game.values.TurnId;
import com.buildingclue.shared.domain.generic.Entity;

public class Turn extends Entity<TurnId> {

  private PlayerId playerId;
  private CurrentPlayer currentPlayer;
  private GameState gameState;

  public Turn(PlayerId playerId, CurrentPlayer currentPlayer, GameState gameState) {
    super(new TurnId());
    this.playerId = playerId;
    this.currentPlayer = currentPlayer;
    this.gameState = gameState;
  }

  public Turn(TurnId id, PlayerId playerId, CurrentPlayer currentPlayer, GameState gameState) {
    super(id);
    this.playerId = playerId;
    this.currentPlayer = currentPlayer;
    this.gameState = gameState;
  }

  public PlayerId getPlayerId() {
    return playerId;
  }

  public void setPlayerId(PlayerId playerId) {
    this.playerId = playerId;
  }

  public CurrentPlayer getCurrentPlayer() {
    return currentPlayer;
  }

  public void setCurrentPlayer(CurrentPlayer currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public GameState getGameState() {
    return gameState;
  }

  public void setGameState(GameState gameState) {
    this.gameState = gameState;
  }
}
