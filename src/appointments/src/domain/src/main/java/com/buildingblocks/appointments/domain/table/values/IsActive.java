package com.buildingblocks.appointments.domain.table.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class IsActive implements IValueObject {
  private final Boolean value;

  private IsActive(Boolean value) {
    this.value = value;
    validate();
  }

  public static IsActive of(Boolean value) {
    return new IsActive(value);
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
