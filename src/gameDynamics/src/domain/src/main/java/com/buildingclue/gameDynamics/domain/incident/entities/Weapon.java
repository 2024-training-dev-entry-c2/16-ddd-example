package com.buildingclue.gameDynamics.domain.incident.entities;

import com.buildingclue.gameDynamics.domain.incident.values.Name;
import com.buildingclue.gameDynamics.domain.incident.values.Type;
import com.buildingclue.gameDynamics.domain.incident.values.WeaponId;
import com.buildingclue.shared.domain.generic.Entity;

public class Weapon extends Entity<WeaponId> {

  private Name name;
  private Type type;

  public Weapon(Name name, Type type) {
    super(new WeaponId());
    this.name = name;
    this.type = type;
  }

  public Weapon(WeaponId id, Name name, Type type) {
    super(id);
    this.name = name;
    this.type = type;
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }
}
