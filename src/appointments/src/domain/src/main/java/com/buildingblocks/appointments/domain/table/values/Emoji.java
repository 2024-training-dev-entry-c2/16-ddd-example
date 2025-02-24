package com.buildingblocks.appointments.domain.table.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Emoji implements IValueObject {
  private final String value;

  private Emoji(String value) {
    this.value = value;
    validate();
  }

  public static Emoji of(String value) {
    return new Emoji(value);
  }

  @Override
  public void validate() {
    if (this.value == null) {
      throw new IllegalArgumentException("The value cant be null");
    }

    if (this.value.isBlank()) {
      throw new IllegalArgumentException("The value cant be blank");
    }
  }

  public String getValue() {
    return value;
  }
}
