package com.buildingclue.gameDynamics.application.game.accusation;

import com.buildingclue.shared.application.Request;

public class AccusationRequest extends Request {
  private final String playerId;
  private final String suspect;
  private final String weapon;
  private final String location;

  public AccusationRequest(String aggregateId, String playerId, String suspect, String weapon, String location) {
    super(aggregateId);
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
