package com.buildingblocks.shared.application.deckOfCards.discardCard;

import com.buildingblocks.shared.application.Request;

public class DiscardCardRequest extends Request {
    private final String cardId;

    public DiscardCardRequest(String aggregateId, String cardId) {
        super(aggregateId);
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }
}
