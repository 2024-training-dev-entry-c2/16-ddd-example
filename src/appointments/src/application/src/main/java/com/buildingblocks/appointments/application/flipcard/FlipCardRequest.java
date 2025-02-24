package com.buildingblocks.appointments.application.flipcard;

import com.buildingblocks.shared.application.Request;

public class FlipCardRequest extends Request {
  private final String cardId;

  public FlipCardRequest(String aggregateId, String cardId) {
    super(aggregateId);
    this.cardId = cardId;
  }

  public String getCardId() {
    return cardId;
  }
}
