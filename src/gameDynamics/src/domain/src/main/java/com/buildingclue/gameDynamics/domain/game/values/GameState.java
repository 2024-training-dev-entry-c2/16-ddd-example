package com.buildingclue.gameDynamics.domain.game.values;

import com.buildingclue.shared.domain.constants.States;
import com.buildingclue.shared.domain.generic.IValueObject;

import java.util.Objects;

import static com.buildingclue.shared.domain.utils.ValidationUtils.validateNotNull;

public class GameState implements IValueObject {

  private final States state;

  public GameState(States state) {
    this.state = state;
    validate();
  }

  public static GameState of(States state) {
    return new GameState(state);
  }

  @Override
  public void validate() {
    validateNotNull(state, "state cannot be null");
  }

  public States getState() {
    return state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GameState gameState = (GameState) o;
    return state == gameState.state; // Suponiendo que el estado es un valor que puede compararse directamente.
  }

  // Sobrescribir hashCode() para que sea consistente con equals()
  @Override
  public int hashCode() {
    return Objects.hash(state);
  }
}
