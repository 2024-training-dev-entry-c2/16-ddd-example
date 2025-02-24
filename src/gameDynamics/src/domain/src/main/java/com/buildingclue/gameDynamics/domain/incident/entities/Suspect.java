package com.buildingclue.gameDynamics.domain.incident.entities;

import com.buildingclue.gameDynamics.domain.incident.values.Name;
import com.buildingclue.gameDynamics.domain.incident.values.Occupation;
import com.buildingclue.gameDynamics.domain.incident.values.SuspectId;
import com.buildingclue.shared.domain.generic.Entity;

public class Suspect extends Entity<SuspectId> {

  private Name name;
  private Occupation occupation;

  public Suspect(Name name, Occupation occupation) {
    super(new SuspectId());
    this.name = name;
    this.occupation = occupation;
  }

  public Suspect(SuspectId id, Name name, Occupation occupation) {
    super(id);
    this.name = name;
    this.occupation = occupation;
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Occupation getOccupation() {
    return occupation;
  }

  public void setOccupation(Occupation occupation) {
    this.occupation = occupation;
  }
}
