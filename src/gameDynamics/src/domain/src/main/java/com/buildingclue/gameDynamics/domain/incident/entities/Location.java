package com.buildingclue.gameDynamics.domain.incident.entities;

import com.buildingclue.gameDynamics.domain.incident.values.Description;
import com.buildingclue.gameDynamics.domain.incident.values.LocationId;
import com.buildingclue.gameDynamics.domain.incident.values.Name;
import com.buildingclue.shared.domain.generic.Entity;

public class Location extends Entity<LocationId> {

  private Name name;
  private Description description;

  public Location(Name name, Description description) {
    super(new LocationId());
    this.name = name;
    this.description = description;
  }

  public Location(LocationId id, Name name, Description description) {
    super(id);
    this.name = name;
    this.description = description;
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Description getDescription() {
    return description;
  }

  public void setDescription(Description description) {
    this.description = description;
  }
}
