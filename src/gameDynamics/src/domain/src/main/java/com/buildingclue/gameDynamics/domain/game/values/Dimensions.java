package com.buildingclue.gameDynamics.domain.game.values;

import com.buildingclue.shared.domain.generic.IValueObject;

import static com.buildingclue.shared.domain.utils.ValidationUtils.validateInteger;

public class Dimensions implements IValueObject {

  private final Integer width;
  private final Integer height;

  public Dimensions(Integer width, Integer height) {
    this.width = width;
    this.height = height;
    validate();
  }

  public static Dimensions of(Integer width, Integer height) {
    return new Dimensions(width, height);
  }

  @Override
  public void validate() {
    validateInteger(width, "width cannot be null or negative");
    validateInteger(height, "height cannot be null or negative");
  }

  public Integer getWidth() {
    return width;
  }

  public Integer getHeight() {
    return height;
  }
}
