package com.buildingblocks.appointments.domain.appointment.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

import java.time.LocalDateTime;

public class Schedule implements IValueObject {
  private final LocalDateTime value;

  private Schedule(LocalDateTime value) {
    this.value = value;
    validate();
  }

  public static Schedule of(LocalDateTime value) {
    return new Schedule(value);
  }

  @Override
  public void validate() {
    // TODO: Validaciones
  }

  public LocalDateTime getValue() {
    return value;
  }
}
