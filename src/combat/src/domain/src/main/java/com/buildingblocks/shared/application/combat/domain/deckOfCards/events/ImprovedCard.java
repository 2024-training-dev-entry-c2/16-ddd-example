package com.buildingblocks.shared.application.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class ImprovedCard  extends DomainEvent {
    private  String deckId;
    private  String cardId;
    private  String upgrade;
    public ImprovedCard() {
        super(EventsEnum.CARD_IMPROVED.name());
    }

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

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setUpgrade(String upgrade) {
        this.upgrade = upgrade;
    }
}
