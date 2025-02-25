package com.buildingblocks.shared.application.combat.endTurn;

import com.buildingblocks.shared.application.Request;

public class EndTurnRequest extends Request {
    private final Integer turnNumber;

    public EndTurnRequest(String aggregateId, Integer turnNumber) {
        super(aggregateId);
        this.turnNumber = turnNumber;
    }

    public Integer getTurnNumber() {
        return turnNumber;
    }
}
