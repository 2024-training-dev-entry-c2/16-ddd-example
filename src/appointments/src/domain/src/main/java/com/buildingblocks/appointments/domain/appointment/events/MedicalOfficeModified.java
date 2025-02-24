package com.buildingblocks.appointments.domain.appointment.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class MedicalOfficeModified extends DomainEvent {
  private final String id;
  private final String street;
  private final String number;
  private final String zip;

  public MedicalOfficeModified(String id, String street, String number, String zip) {
    super(EventsEnum.MEDICAL_OFFICE_MODIFIED.name());
    this.id = id;
    this.street = street;
    this.number = number;
    this.zip = zip;
  }

  public String getId() {
    return id;
  }

  public String getStreet() {
    return street;
  }

  public String getNumber() {
    return number;
  }

  public String getZip() {
    return zip;
  }
}
