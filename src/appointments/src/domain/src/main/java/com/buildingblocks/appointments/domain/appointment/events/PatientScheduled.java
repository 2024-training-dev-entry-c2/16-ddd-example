package com.buildingblocks.appointments.domain.appointment.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class PatientScheduled extends DomainEvent {
  private final String firstName;
  private final String lastName;
  private final String email;
  private final Integer age;
  private final String reason;

  public PatientScheduled(String firstName, String lastName, String email, Integer age, String reason) {
    super(EventsEnum.PATIENT_SCHEDULED.name());
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
    this.reason = reason;
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

  public Integer getAge() {
    return age;
  }

  public String getReason() {
    return reason;
  }
}
