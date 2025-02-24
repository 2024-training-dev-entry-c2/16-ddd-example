package com.zombicide.actions.application.player.addsurvivor;

import com.zombicide.shared.application.Request;

public class AddSurvivorRequest extends Request {
  private final String survivorId;
  private final String playerName;

  public AddSurvivorRequest(String aggregateId, String survivorId, String playerName) {
    super(aggregateId);
    this.survivorId = survivorId;
    this.playerName = playerName;
  }

  public String getSurvivorId() {
    return survivorId;
  }

  public String getPlayerName() {
    return playerName;
  }
}
