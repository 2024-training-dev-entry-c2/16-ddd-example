package com.buildingclue.gameDynamics.domain.incident.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class SuspectIdentified extends DomainEvent {

  private final String incidentId;
  private final String suspectName;

  public SuspectIdentified(String incidentId, String suspectName) {
    super(EventsEnum.SUSPECT_IDENTIFED.name());
    this.incidentId = incidentId;
    this.suspectName = suspectName;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public String getSuspectName() {
    return suspectName;
  }
}
