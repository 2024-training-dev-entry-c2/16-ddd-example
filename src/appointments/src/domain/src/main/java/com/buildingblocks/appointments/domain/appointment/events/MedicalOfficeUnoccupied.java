package com.buildingblocks.appointments.domain.appointment.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class MedicalOfficeUnoccupied extends DomainEvent {
  private final String id;

  public MedicalOfficeUnoccupied(String id) {
    super(EventsEnum.MEDICAL_OFFICE_UNOCCUPIED.name());
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
