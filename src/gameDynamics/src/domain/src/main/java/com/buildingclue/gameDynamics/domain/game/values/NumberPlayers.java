package com.buildingclue.gameDynamics.domain.game.values;

import com.buildingclue.shared.domain.generic.IValueObject;

import static com.buildingclue.shared.domain.utils.ValidationUtils.validateRange;

public class NumberPlayers implements IValueObject {

  private final Integer number;

  public NumberPlayers(Integer number) {
    this.number = number;
    validate();
  }

  public static NumberPlayers of(Integer number) {
    return new NumberPlayers(number);
  }

  @Override
  public void validate() {
      validateRange(number, 2, 6, "number cannot be null or negative");
  }

  public Integer getNumber() {
    return number;
  }
}
