package com.buildingblocks.shared.application.deckOfCards.improveCard;

import com.buildingblocks.shared.application.Request;

public class ImproveCardRequest extends Request {
    private final String cardId;
    private final String improvement;

    public ImproveCardRequest(String aggregateId, String cardId, String improvement) {
        super(aggregateId);
        this.cardId = cardId;
        this.improvement = improvement;
    }

    public String getCardId() {
        return cardId;
    }

    public String getImprovement() {
        return improvement;
    }
}
