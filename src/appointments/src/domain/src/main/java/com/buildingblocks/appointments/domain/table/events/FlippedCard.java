package com.buildingblocks.appointments.domain.table.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class FlippedCard extends DomainEvent {
  private final String cardId;

  public FlippedCard(String cardId) {
    super(EventsEnum.FLIPPED_CARD.name());
    this.cardId = cardId;
  }

  public String getCardId() {
    return cardId;
  }
}
