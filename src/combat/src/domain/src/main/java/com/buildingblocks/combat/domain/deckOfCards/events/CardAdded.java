package com.buildingblocks.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class CardAdded extends DomainEvent {
    private  final String cardId;
    private  final String deckId;

    public CardAdded(String cardId, String deckId) {
        super(EventsEnum.CARD_ADDED.name());
        this.cardId = cardId;
        this.deckId = deckId;
    }

    public String getCardId() {
        return cardId;
    }

    public String getDeckId() {
        return deckId;
    }
}
