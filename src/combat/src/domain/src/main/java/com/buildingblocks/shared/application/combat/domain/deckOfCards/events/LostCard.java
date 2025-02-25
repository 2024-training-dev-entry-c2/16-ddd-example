package com.buildingblocks.shared.application.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class LostCard extends DomainEvent {
    private  String cardId;
    public LostCard() {
        super(EventsEnum.LOST_CARD.name());
    }
    public LostCard(String cardId) {
        super(EventsEnum.LOST_CARD.name());
        this.cardId = cardId;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }
}
