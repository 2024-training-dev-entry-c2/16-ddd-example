package com.buildingblocks.appointments.domain.table.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Score implements IValueObject {
  private final Integer value;

  private Score(Integer value) {
    this.value = value;
    validate();
  }

  public static Score of(Integer value) {
    return new Score(value);
  }

  @Override
  public void validate() {
    if (this.value == null) {
      throw new IllegalArgumentException("The score cant be null");
    }

    if (this.value < 0) {
      throw new IllegalArgumentException("The score cant be less than 0");
    }
  }

  public Integer getValue() {
    return value;
  }
}
