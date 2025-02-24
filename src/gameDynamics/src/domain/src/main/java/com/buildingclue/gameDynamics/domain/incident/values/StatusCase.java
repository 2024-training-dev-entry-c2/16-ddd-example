package com.buildingclue.gameDynamics.domain.incident.values;

import com.buildingclue.shared.domain.constants.States;
import com.buildingclue.shared.domain.generic.IValueObject;

import static com.buildingclue.shared.domain.utils.ValidationUtils.validateNotNull;

public class StatusCase implements IValueObject {

  private final States state;

  public StatusCase(States state) {
    this.state = state;
    validate();
  }

  public static StatusCase of(States state) {
    return new StatusCase(state);
  }

  @Override
  public void validate() {
    validateNotNull(state, "state cannot be null");
  }

  public States getState() {
    return state;
  }
}
