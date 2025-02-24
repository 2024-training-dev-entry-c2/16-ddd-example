package com.buildingclue.gameDynamics.application.incident.identifyweapon;

import com.buildingclue.shared.application.Request;

public class IdentifyWeaponRequest extends Request {

  private final String weaponName;

  public IdentifyWeaponRequest(String aggregateId, String weaponName) {
    super(aggregateId);
    this.weaponName = weaponName;
  }

  public String getWeaponName() {
    return weaponName;
  }
}
