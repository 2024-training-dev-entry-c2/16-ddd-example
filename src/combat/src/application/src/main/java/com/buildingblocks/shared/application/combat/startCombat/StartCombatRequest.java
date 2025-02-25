package com.buildingblocks.shared.application.combat.startCombat;

import com.buildingblocks.shared.application.Request;
import reactor.util.annotation.NonNull;

public class StartCombatRequest extends Request {

    private final String scenarioId;

    public StartCombatRequest(String aggregateId, String scenarioId) {
        super(aggregateId);
        this.scenarioId = scenarioId;
    }

    public String getScenarioId() {
        return scenarioId;
    }
}
