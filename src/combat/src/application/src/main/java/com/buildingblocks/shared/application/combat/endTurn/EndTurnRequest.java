package com.buildingblocks.shared.application.combat.endTurn;

import com.buildingblocks.shared.application.Request;

public class EndTurnRequest extends Request {
    public EndTurnRequest(String aggregateId) {
        super(aggregateId);
    }
}
