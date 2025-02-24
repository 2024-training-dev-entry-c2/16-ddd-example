package com.buildingblocks.shared.application.combat.domain.combat.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

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
        Validator.validateTextNotNull(id);
    }

    public String getId() {
        return id;
    }
}
