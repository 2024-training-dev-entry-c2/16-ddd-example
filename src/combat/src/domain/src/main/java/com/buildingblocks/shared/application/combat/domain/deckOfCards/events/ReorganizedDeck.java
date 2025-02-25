package com.buildingblocks.shared.application.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class  ReorganizedDeck  extends DomainEvent {
    private  String deckId;

    public ReorganizedDeck() {
        super(EventsEnum.DECK_REORGANIZED.name());
    }

    public ReorganizedDeck( String deckId) {
        super(EventsEnum.DECK_REORGANIZED.name());
        this.deckId = deckId;
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }
}
