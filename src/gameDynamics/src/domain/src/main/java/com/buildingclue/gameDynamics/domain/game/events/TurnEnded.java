package com.buildingclue.gameDynamics.domain.game.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class TurnEnded extends DomainEvent {

  private final String playerId;
  private final Integer turnNumber;
  private final String reason;

  public TurnEnded(String playerId, Integer turnNumber, String reason) {
    super(EventsEnum.TURN_ENDED.name());
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
