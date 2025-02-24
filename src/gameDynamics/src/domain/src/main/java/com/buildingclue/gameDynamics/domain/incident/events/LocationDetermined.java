package com.buildingclue.gameDynamics.domain.incident.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class LocationDetermined extends DomainEvent {

  private final String incidentId;
  private final String locationName;

  public LocationDetermined(String incidentId, String locationName) {
    super(EventsEnum.LOCATION_DETERMINED.name());
    this.incidentId = incidentId;
    this.locationName = locationName;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public String getLocationName() {
    return locationName;
  }
}
