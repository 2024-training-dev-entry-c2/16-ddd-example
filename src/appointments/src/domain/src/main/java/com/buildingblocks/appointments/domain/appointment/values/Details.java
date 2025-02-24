package com.buildingblocks.appointments.domain.appointment.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Details implements IValueObject {
  private final String value;

  private Details(String value) {
    this.value = value;
  }

  public static Details of(String value) {
    return new Details(value);
  }

  @Override
  public void validate() {
    if (this.value == null) {
      throw new IllegalArgumentException("The street cant be null");
    }

    if (this.value.isBlank()) {
      throw new IllegalArgumentException("The street cant be blank");
    }

    if (!this.value.matches("^[a-zA-Z0-9\\s]*$")) {
      throw new IllegalArgumentException("The street can only contain alphanumeric characters and spaces");
    }
  }
}
