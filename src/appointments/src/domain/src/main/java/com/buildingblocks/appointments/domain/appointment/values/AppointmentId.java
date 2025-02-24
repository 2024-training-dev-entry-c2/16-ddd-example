package com.buildingblocks.appointments.domain.appointment.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class AppointmentId extends Identity {

  public AppointmentId() {
    super();
  }

  private AppointmentId(String value) {
    super(value);
  }

  public static AppointmentId of(String value) {
    return new AppointmentId(value);
  }
}
