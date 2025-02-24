package com.buildingblocks.shared.application.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class RecoveredCard extends DomainEvent {
    private final String cardId;
    private final String deckId;

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
}
