package com.buildingclue.gameDynamics.domain.game.events;
import com.buildingclue.shared.domain.generic.DomainEvent;

public class AccusationMade extends DomainEvent{

  private final String playerId;
  private final String accusedSuspect;
  private final String accusedWeapon;
  private final String accusedLocation;

  public AccusationMade(String playerId, String accusedSuspect, String accusedWeapon, String accusedLocation) {
    super(EventsEnum.ACCUSATION_MADE.name());
    this.playerId = playerId;
    this.accusedSuspect = accusedSuspect;
    this.accusedWeapon = accusedWeapon;
    this.accusedLocation = accusedLocation;
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getAccusedSuspect() {
    return accusedSuspect;
  }

  public String getAccusedWeapon() {
    return accusedWeapon;
  }

  public String getAccusedLocation() {
    return accusedLocation;
  }
}
