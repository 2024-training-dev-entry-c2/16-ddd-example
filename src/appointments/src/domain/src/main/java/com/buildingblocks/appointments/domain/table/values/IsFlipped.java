package com.buildingblocks.appointments.domain.table.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class IsFlipped implements IValueObject {
  private final Boolean value;

  private IsFlipped(Boolean value) {
    this.value = value;
    validate();
  }

  public static IsFlipped of(Boolean value) {
    return new IsFlipped(value);
  }

  @Override
  public void validate() {
    if (this.value == null) {
      throw new IllegalArgumentException("The value cant be null");
    }
  }

  public Boolean getValue() {
    return value;
  }
}
