package com.buildingblocks.appointments.domain.appointment.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class SpecialistAssigned extends DomainEvent {
  private final String firstName;
  private final String lastName;
  private final String email;
  private final String specialty;

  public SpecialistAssigned(String firstName, String lastName, String email, String specialty) {
    super(EventsEnum.SPECIALIST_ASSIGNED.name());
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.specialty = specialty;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getSpecialty() {
    return specialty;
  }
}
