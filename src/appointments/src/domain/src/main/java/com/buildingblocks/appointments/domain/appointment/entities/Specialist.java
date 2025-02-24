package com.buildingblocks.appointments.domain.appointment.entities;

import com.buildingblocks.appointments.domain.appointment.values.Email;
import com.buildingblocks.appointments.domain.appointment.values.FirstName;
import com.buildingblocks.appointments.domain.appointment.values.LastName;
import com.buildingblocks.appointments.domain.appointment.values.SpecialistId;
import com.buildingblocks.appointments.domain.appointment.values.Specialty;
import com.buildingblocks.shared.domain.generic.Entity;

public class Specialist extends Entity<SpecialistId> {
  private FirstName firstName;
  private LastName lastName;
  private Email email;
  private Specialty specialty;

  public Specialist(FirstName firstName, LastName lastName, Email email, Specialty specialty) {
    super(new SpecialistId());
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.specialty = specialty;
  }

  public Specialist(SpecialistId identity, FirstName firstName, LastName lastName, Email email, Specialty specialty) {
    super(identity);
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.specialty = specialty;
  }

  public float calculate() {
    return 0.0f;
  }

  public FirstName getFirstName() {
    return firstName;
  }

  public void setFirstName(FirstName firstName) {
    this.firstName = firstName;
  }

  public LastName getLastName() {
    return lastName;
  }

  public void setLastName(LastName lastName) {
    this.lastName = lastName;
  }

  public Email getEmail() {
    return email;
  }

  public void setEmail(Email email) {
    this.email = email;
  }

  public Specialty getSpecialty() {
    return specialty;
  }

  public void setSpecialty(Specialty specialty) {
    this.specialty = specialty;
  }
}
