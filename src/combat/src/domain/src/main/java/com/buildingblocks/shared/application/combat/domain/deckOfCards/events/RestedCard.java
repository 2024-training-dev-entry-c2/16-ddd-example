package com.buildingblocks.shared.application.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class RestedCard extends DomainEvent {
    private  String deckId;
    private   Boolean isLongRest;
    public RestedCard() {
        super(EventsEnum.CARDS_RESTED.name());
    }
    public RestedCard( String deckId, Boolean isLongRest) {
        super(EventsEnum.CARDS_RESTED.name());
        this.deckId = deckId;
        this.isLongRest = isLongRest;
    }

    public String getDeckId() {
        return deckId;
    }

    public Boolean isLongRest() {
        return isLongRest;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public Boolean getLongRest() {
        return isLongRest;
    }

    public void setLongRest(Boolean longRest) {
        isLongRest = longRest;
    }
}
