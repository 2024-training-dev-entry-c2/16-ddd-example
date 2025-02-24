package com.buildingblocks.shared.application.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class CardDiscarded extends DomainEvent {
    private  final String cardId;

    public CardDiscarded( String cardId) {
        super(EventsEnum.CARD_DISCARDED.name());
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }
}
