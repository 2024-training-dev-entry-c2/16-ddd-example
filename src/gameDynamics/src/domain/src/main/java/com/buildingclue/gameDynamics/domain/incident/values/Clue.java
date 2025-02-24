package com.buildingclue.gameDynamics.domain.incident.values;

import com.buildingclue.shared.domain.generic.IValueObject;

import static com.buildingclue.shared.domain.utils.ValidationUtils.validateString;

public class Clue implements IValueObject {

  private final String value;

  public Clue(String value) {
    this.value = value;
    validate();
  }

  public static Clue of(String value) {
    return new Clue(value);
  }

  @Override
  public void validate() {
    validateString(value, "value cannot be null or empty");
  }

  public String getValue() {
    return value;
  }
}
