package com.buildingblocks.appointments.domain.table.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class FlippedCard extends DomainEvent {
  private String cardId;

  public FlippedCard() {
    super(EventsEnum.FLIPPED_CARD.name());
  }

  public FlippedCard(String cardId) {
    super(EventsEnum.FLIPPED_CARD.name());
    this.cardId = cardId;
  }

  public String getCardId() {
    return cardId;
  }

  public void setCardId(String cardId) {
    this.cardId = cardId;
  }
}
