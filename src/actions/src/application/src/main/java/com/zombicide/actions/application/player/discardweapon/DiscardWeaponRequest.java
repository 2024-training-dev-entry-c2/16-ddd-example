package com.zombicide.actions.application.player.discardweapon;

import com.zombicide.shared.application.Request;

public class DiscardWeaponRequest extends Request {
  private final String weaponId;
  private final String survivorId;

  public DiscardWeaponRequest(String weaponId, String survivorId) {
    super(null);
    this.weaponId = weaponId;
    this.survivorId = survivorId;
  }

  public String getWeaponId() {
    return weaponId;
  }

  public String getSurvivorId() {
    return survivorId;
  }
}
