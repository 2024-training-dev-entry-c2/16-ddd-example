package com.buildingblocks.shared.application.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class CardRemoved extends DomainEvent {

    private   String cardId;
    private   String deckId;

    public CardRemoved() {
        super(EventsEnum.CARD_REMOVED.name());
    }

    public CardRemoved(String deckId, String cardId) {
        super(EventsEnum.CARD_REMOVED.name());
        this.cardId = cardId;
        this.deckId = deckId;
    }

    public String getCardId() {
        return cardId;
    }

    public String getDeckId() {
        return deckId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }
}
