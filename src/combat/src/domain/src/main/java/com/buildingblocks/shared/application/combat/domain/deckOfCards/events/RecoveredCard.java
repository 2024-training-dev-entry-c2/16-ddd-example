package com.buildingblocks.shared.application.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class RecoveredCard extends DomainEvent {
    private  String cardId;
    private  String deckId;
    public RecoveredCard() {
        super(EventsEnum.CARD_RECOVERED.name());
    }
    public RecoveredCard( String deckId,String cardId) {
        super(EventsEnum.CARD_RECOVERED.name());
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
