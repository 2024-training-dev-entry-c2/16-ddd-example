package com.buildingclue.gameDynamics.domain.incident.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class WeaponIdentified extends DomainEvent {

  private final String incidentId;
  private final String weaponName;

  public WeaponIdentified(String incidentId, String weaponName) {
    super(EventsEnum.WEAPON_IDENTIFED.name());
    this.incidentId = incidentId;
    this.weaponName = weaponName;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public String getWeaponName() {
    return weaponName;
  }
}
