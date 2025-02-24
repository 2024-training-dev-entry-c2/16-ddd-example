package com.buildingclue.gameDynamics.domain.game.values;

import com.buildingclue.shared.domain.generic.IValueObject;

import static com.buildingclue.shared.domain.utils.ValidationUtils.validateString;

public class Room implements IValueObject {

  private final String name;

  public Room(String name) {
    this.name = name;
    validate();
  }

  public static Room of(String name) {
    return new Room(name);
  }

  @Override
  public void validate() {
    validateString(name, "Room cannot be null or empty");
  }

  public String getName() {
    return name;
  }
}
