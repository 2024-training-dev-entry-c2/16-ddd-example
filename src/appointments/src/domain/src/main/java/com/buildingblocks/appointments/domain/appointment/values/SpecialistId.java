package com.buildingblocks.appointments.domain.appointment.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class SpecialistId extends Identity {

  public SpecialistId() {
    super();
  }

  private SpecialistId(String value) {
    super(value);
  }

  public static SpecialistId of(String value) {
    return new SpecialistId(value);
  }
}