package com.buildingblocks.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class RestedCard extends DomainEvent {
    private final String deckId;

    public RestedCard(String deckId) {
        super(EventsEnum.CARDS_RESTED.name());
        this.deckId = deckId;
    }

    public String getDeckId() {
        return deckId;
    }
}
