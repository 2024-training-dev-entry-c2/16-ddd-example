package com.buildingblocks.appointments.domain.appointment.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class SpecialistModified extends DomainEvent {
  private final String id;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final String specialty;


  public SpecialistModified(String id, String firstName, String lastName, String email, String specialty) {
    super(EventsEnum.SPECIALIST_MODIFIED.name());
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.specialty = specialty;
  }

  public String getId() {
    return id;
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
