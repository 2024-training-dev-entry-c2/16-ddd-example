package com.buildingblocks.appointments.domain.table.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Turn implements IValueObject {
  private final String playerId;

  private Turn(String playerId) {
    this.playerId = playerId;
    validate();
  }

  public static Turn of(String playerId) {
    return new Turn(playerId);
  }

  @Override
  public void validate() {
    if (this.playerId == null) {
      throw new IllegalArgumentException("The playerId cant be null");
    }

    if (this.playerId.isBlank()) {
      throw new IllegalArgumentException("The playerId cant be blank");
    }
  }

  public String getPlayerId() {
    return playerId;
  }
}
