package com.zombicide.actions.application.player.obtainweapon;

import com.zombicide.shared.application.Request;

public class ObtainWaponRequest extends Request {
  private final String survivorId;

  public ObtainWaponRequest(String survivorId) {
    super(null);
    this.survivorId = survivorId;
  }

  public String getSurvivorId() {
    return survivorId;
  }
}