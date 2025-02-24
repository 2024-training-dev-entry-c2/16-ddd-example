package com.buildingblocks.shared.application.shared.deckOfCards;

import java.util.List;

public class DeckOfCardsResponse {

    private final String deckId;
    private final List<String> skillCards; // IDs de las cartas en el mazo
    private final List<String> discardedCards; // IDs de las cartas descartadas
    private final List<String> lostCards; // IDs de las cartas perdidas
    private final Object eventDetails; // Detalles específicos del evento

    public DeckOfCardsResponse(String deckId, List<String> skillCards, List<String> discardedCards, List<String> lostCards, Object eventDetails) {
        this.deckId = deckId;
        this.skillCards = skillCards;
        this.discardedCards = discardedCards;
        this.lostCards = lostCards;
        this.eventDetails = eventDetails;
    }

    // Getters
    public String getDeckId() {
        return deckId;
    }

    public List<String> getSkillCards() {
        return skillCards;
    }

    public List<String> getDiscardedCards() {
        return discardedCards;
    }

    public List<String> getLostCards() {
        return lostCards;
    }

    public Object getEventDetails() {
        return eventDetails;
    }

}
