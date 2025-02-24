package com.buildingclue.gameDynamics.domain.incident.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class InvestigationStarted extends DomainEvent {

  private final String incidentId;

  public InvestigationStarted(String incidentId) {
    super(EventsEnum.INVESTIGATION_STARTED.name());
    this.incidentId = incidentId;
  }

  public String getIncidentId() {
    return incidentId;
  }
}
