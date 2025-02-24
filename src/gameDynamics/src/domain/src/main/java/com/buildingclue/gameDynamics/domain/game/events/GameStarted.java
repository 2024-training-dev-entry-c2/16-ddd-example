package com.buildingclue.gameDynamics.domain.game.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class GameStarted extends DomainEvent {

  private final String gameId;

  public GameStarted(String gameId) {
    super(EventsEnum.GAME_STARTED.name());
    this.gameId = gameId;
  }

  public String getGameId() {
    return gameId;
  }
}
