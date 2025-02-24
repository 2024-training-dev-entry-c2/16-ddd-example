package com.buildingclue.gameDynamics.domain.game.values;

import com.buildingclue.shared.domain.generic.IValueObject;

import static com.buildingclue.shared.domain.utils.ValidationUtils.validateInteger;

public class Positions implements IValueObject {

  private final Integer x;
  private final Integer y;

  public Positions(Integer x, Integer y) {
    this.x = x;
    this.y = y;
    validate();
  }

  public static Positions of(Integer x, Integer y) {
    return new Positions(x, y);
  }

  @Override
  public void validate() {
    validateInteger(x, "x cannot be null or negative");
    validateInteger(y, "y cannot be null or negative");
  }

  public Integer getX() {
    return x;
  }

  public Integer getY() {
    return y;
  }
}
