package com.buildingblocks.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class RestedCard extends DomainEvent {
    private final String deckId;
    private  final Boolean isLongRest;

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
}
