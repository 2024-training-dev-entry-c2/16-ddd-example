package com.buildingblocks.shared.application.combat.endCombat;

import com.buildingblocks.shared.application.Request;

public class EndCombatRequest extends Request {
    public EndCombatRequest(String aggregateId) {
        super(aggregateId);
    }
}
