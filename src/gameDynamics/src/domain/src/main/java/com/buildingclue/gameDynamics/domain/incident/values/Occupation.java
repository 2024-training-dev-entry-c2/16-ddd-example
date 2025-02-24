package com.buildingclue.gameDynamics.domain.incident.values;

import com.buildingclue.shared.domain.generic.IValueObject;

import static com.buildingclue.shared.domain.utils.ValidationUtils.validateString;

public class Occupation implements IValueObject {

  private final String value;

  public Occupation(String value) {
    this.value = value;
    validate();
  }

  public static Occupation of(String value) {
    return new Occupation(value);
  }

  @Override
  public void validate() {
    validateString(value, "value cannot be null or empty");
  }

  public String getValue() {
    return value;
  }
}
