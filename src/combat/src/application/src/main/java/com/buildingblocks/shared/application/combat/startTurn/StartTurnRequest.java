package com.buildingblocks.shared.application.combat.startTurn;

import com.buildingblocks.shared.application.Request;

public class StartTurnRequest extends Request {
        public StartTurnRequest(String aggregateId) {
            super(aggregateId);
        }
}
