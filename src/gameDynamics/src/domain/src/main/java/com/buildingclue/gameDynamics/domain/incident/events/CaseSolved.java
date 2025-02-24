package com.buildingclue.gameDynamics.domain.incident.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class CaseSolved extends DomainEvent {

  private final String incidentId;

  public CaseSolved(String incidentId) {
    super(EventsEnum.CASE_SOLVED.name());
    this.incidentId = incidentId;
  }

  public String getIncidentId() {
    return incidentId;
  }
}
