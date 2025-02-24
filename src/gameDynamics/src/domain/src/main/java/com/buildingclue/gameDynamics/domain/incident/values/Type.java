package com.buildingclue.gameDynamics.domain.incident.values;

import com.buildingclue.shared.domain.generic.IValueObject;

import static com.buildingclue.shared.domain.utils.ValidationUtils.validateString;

public class Type implements IValueObject {

  private final String value;

  public Type(String value) {
    this.value = value;
    validate();
  }

  public static Type of(String value) {
    return new Type(value);
  }

  @Override
  public void validate() {
    validateString(value, "value cannot be null or empty");
  }

  public String getValue() {
    return value;
  }
}
