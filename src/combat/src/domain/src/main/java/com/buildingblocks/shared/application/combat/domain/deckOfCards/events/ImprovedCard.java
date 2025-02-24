package com.buildingblocks.shared.application.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class ImprovedCard  extends DomainEvent {
    private final String deckId;
    private final String cardId;
    private final String upgrade;

    public ImprovedCard( String deckId, String cardId, String upgrade) {
        super(EventsEnum.CARD_IMPROVED.name());
        this.deckId = deckId;
        this.cardId = cardId;
        this.upgrade = upgrade;
    }

    public String getDeckId() {
        return deckId;
    }

    public String getCardId() {
        return cardId;
    }

    public String getUpgrade() {
        return upgrade;
    }
}
