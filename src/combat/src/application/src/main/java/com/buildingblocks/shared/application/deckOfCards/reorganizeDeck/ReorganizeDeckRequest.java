package com.buildingblocks.shared.application.deckOfCards.reorganizeDeck;

import com.buildingblocks.shared.application.Request;

public class ReorganizeDeckRequest extends Request {
    private final String state;

    public ReorganizeDeckRequest(String aggregateId, String state) {
        super(aggregateId);
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
