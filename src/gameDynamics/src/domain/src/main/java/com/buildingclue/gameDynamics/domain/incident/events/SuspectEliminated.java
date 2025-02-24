package com.buildingclue.gameDynamics.domain.incident.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class SuspectEliminated extends DomainEvent {

  private final String incidentId;
  private final String suspectName;

  public SuspectEliminated(String incidentId, String suspectName) {
    super(EventsEnum.SUSPECT_ELIMINATED.name());
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
