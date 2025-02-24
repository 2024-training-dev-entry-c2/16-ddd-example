package com.buildingclue.gameDynamics.domain.game.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class MoveMade extends DomainEvent {

  private final String playerId;
  private final String fromPosition;
  private final String toPosition;

  public MoveMade(String playerId, String fromPosition, String toPosition) {
    super(EventsEnum.MOVE_MADE.name());
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
