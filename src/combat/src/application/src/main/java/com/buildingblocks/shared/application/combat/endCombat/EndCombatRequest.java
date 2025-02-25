package com.buildingblocks.shared.application.combat.endCombat;

import com.buildingblocks.shared.application.Request;

public class EndCombatRequest extends Request {
    private final String state;

    public EndCombatRequest(String aggregateId, String state) {
        super(aggregateId);
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
