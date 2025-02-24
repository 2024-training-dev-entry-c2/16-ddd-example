package com.buildingclue.gameDynamics.application.incident.identifyweapon;

public class IdentifyWeaponResponse {
  private final String incidentId;
  private final String weaponName;
  private final boolean success;

  public IdentifyWeaponResponse(String incidentId, String weaponName, boolean success) {
    this.incidentId = incidentId;
    this.weaponName = weaponName;
    this.success = success;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public String getWeaponName() {
    return weaponName;
  }

  public boolean isSuccess() {
    return success;
  }
}
