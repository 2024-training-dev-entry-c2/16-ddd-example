package com.buildingblocks.shared.application.deckOfCards.loseCard;

import com.buildingblocks.shared.application.Request;

public class loseCardRequest extends Request {
    private final String cardId;

    public loseCardRequest(String aggregateId, String cardId) {
        super(aggregateId);
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }
}
