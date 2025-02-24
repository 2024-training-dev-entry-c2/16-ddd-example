package com.buildingblocks.shared.application.deckOfCards.removeCard;

import com.buildingblocks.shared.application.Request;

public class RemoveCardRequest extends Request {
    private final String id;

    public RemoveCardRequest(String aggregateId, String id) {
        super(aggregateId);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
