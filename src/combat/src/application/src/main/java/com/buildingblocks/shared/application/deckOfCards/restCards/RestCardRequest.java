package com.buildingblocks.shared.application.deckOfCards.restCards;

import com.buildingblocks.shared.application.Request;


public class RestCardRequest extends Request {
    private final Boolean longRest;

    public RestCardRequest(String aggregateId, Boolean longRest) {
        super(aggregateId);
        this.longRest = longRest;
    }

    public Boolean getLongRest() {
        return longRest;
    }
}
