package com.buildingblocks.shared.application.combat.startTurn;

import com.buildingblocks.shared.application.Request;

public class StartTurnRequest extends Request {
     private final Integer turnNumber;

    public StartTurnRequest(String aggregateId, Integer turnNumber) {
        super(aggregateId);
        this.turnNumber = turnNumber;
    }

    public Integer getTurnNumber() {
        return turnNumber;
    }


}
