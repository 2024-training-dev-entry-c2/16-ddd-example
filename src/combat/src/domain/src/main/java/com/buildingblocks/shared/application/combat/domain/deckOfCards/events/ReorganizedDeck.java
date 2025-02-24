package com.buildingblocks.shared.application.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class  ReorganizedDeck  extends DomainEvent {
    private final String deckId;

    public ReorganizedDeck( String deckId) {
        super(EventsEnum.DECK_REORGANIZED.name());
        this.deckId = deckId;
    }

    public String getDeckId() {
        return deckId;
    }
}
