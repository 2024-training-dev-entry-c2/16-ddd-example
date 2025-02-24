package com.buildingblocks.appointments.domain.appointment.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class MedicalOfficeAssigned extends DomainEvent {
  private final String street;
  private final String number;
  private final String zip;

  public MedicalOfficeAssigned(String street, String number, String zip) {
    super(EventsEnum.MEDICAL_OFFICE_ASSIGNED.name());
    this.street = street;
    this.number = number;
    this.zip = zip;
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