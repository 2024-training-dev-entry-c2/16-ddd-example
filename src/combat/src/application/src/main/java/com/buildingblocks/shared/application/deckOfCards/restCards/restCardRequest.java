package com.buildingblocks.shared.application.deckOfCards.restCards;

import com.buildingblocks.shared.application.Request;


public class restCardRequest extends Request {
    private final Boolean longRest;

    public restCardRequest(String aggregateId, Boolean longRest) {
        super(aggregateId);
        this.longRest = longRest;
    }

    public Boolean getLongRest() {
        return longRest;
    }
}
