package com.buildingclue.gameDynamics.domain.incident.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class ClueDiscovered extends DomainEvent {

  private final String incidentId;
  private final String clue;

  public ClueDiscovered(String incidentId, String clue) {
    super(EventsEnum.CLUE_DISCOVERED.name());
    this.incidentId = incidentId;
    this.clue = clue;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public String getClue() {
    return clue;
  }
}
