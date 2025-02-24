package com.buildingblocks.shared.application.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class LostCard extends DomainEvent {
    private final String cardId;

    public LostCard(String cardId) {
        super(EventsEnum.LOST_CARD.name());
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }
}
