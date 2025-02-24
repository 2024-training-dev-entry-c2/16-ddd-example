package com.buildingclue.gameDynamics.domain.game.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class TurnStarted extends DomainEvent {
  private final String playerId;
  private final Integer turnNumber;

  public TurnStarted(String currentPlayer, String playerId, Integer turnNumber) {
    super(EventsEnum.TURN_STARTED.name());
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
