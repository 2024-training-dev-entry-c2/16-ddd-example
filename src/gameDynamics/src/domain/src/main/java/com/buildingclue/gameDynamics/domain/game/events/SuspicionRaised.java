package com.buildingclue.gameDynamics.domain.game.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class SuspicionRaised extends DomainEvent {

  private final String playerId;
  private final String suspect;
  private final String weapon;
  private final String location;

  public SuspicionRaised(String playerId, String suspect, String weapon, String location) {
    super(EventsEnum.SUSPICION_RAISED.name());
    this.playerId = playerId;
    this.suspect = suspect;
    this.weapon = weapon;
    this.location = location;
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getSuspect() {
    return suspect;
  }

  public String getWeapon() {
    return weapon;
  }

  public String getLocation() {
    return location;
  }
}
