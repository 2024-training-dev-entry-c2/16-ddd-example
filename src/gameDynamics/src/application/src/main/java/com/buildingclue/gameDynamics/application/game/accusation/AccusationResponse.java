package com.buildingclue.gameDynamics.application.game.accusation;

public class AccusationResponse {
  private final String gameId;
  private final String playerId;
  private final String suspect;
  private final String weapon;
  private final String location;
  private final Boolean isAccusationCorrect;

  public AccusationResponse(String gameId, String playerId, String suspect, String weapon, String location, Boolean isAccusationCorrect) {
    this.gameId = gameId;
    this.playerId = playerId;
    this.suspect = suspect;
    this.weapon = weapon;
    this.location = location;
    this.isAccusationCorrect = isAccusationCorrect;
  }

  public String getGameId() {
    return gameId;
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

  public Boolean getIsAccusationCorrect() {
    return isAccusationCorrect;
  }
}
