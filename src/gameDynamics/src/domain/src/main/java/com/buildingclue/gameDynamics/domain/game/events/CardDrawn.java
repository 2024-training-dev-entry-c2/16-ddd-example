package com.buildingclue.gameDynamics.domain.game.events;

import com.buildingclue.shared.domain.generic.DomainEvent;

public class CardDrawn extends DomainEvent {
  private final String playerId;
  private final String cardType;
  private final String cardName;

  public CardDrawn(String playerId, String cardType, String cardName) {
    super(EventsEnum.CARD_DRAWN.name());
    this.playerId = playerId;
    this.cardType = cardType;
    this.cardName = cardName;
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getCardType() {
    return cardType;
  }

  public String getCardName() {
    return cardName;
  }
}
