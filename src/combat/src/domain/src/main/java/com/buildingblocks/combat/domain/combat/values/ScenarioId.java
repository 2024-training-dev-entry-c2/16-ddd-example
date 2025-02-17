package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class ScenarioId implements IValueObject {
    private final String id;

    private ScenarioId(String id) {
        this.id = id;
    }

    public static ScenarioId of(String id) {
        ScenarioId scenarioId = new ScenarioId(id);
        scenarioId.validate();
        return scenarioId;
    }

    @Override
    public void validate() {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Scenario ID cannot be null or empty.");
        }
    }

    public String getId() {
        return id;
    }
}
