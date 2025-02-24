package com.zombicide.actions.application.player.obtainweapon;

import com.zombicide.shared.application.Request;

public class ObtainWaponRequest extends Request {
  private final String survivorId;

  public ObtainWaponRequest(String aggregateId, String survivorId) {
    super(aggregateId);
    this.survivorId = survivorId;
  }

  public String getSurvivorId() {
    return survivorId;
  }
}