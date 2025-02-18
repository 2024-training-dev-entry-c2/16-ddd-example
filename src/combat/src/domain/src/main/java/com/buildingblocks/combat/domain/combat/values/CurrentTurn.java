package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class CurrentTurn implements IValueObject {
    private final int value;

    private CurrentTurn(int id) {
        this.value = id;
    }

    public static CurrentTurn of(int id) {
        CurrentTurn scenarioId = new CurrentTurn(id);
        scenarioId.validate();
        return scenarioId;
    }

    @Override
    public void validate() {
        if (value == 0) {
            throw new IllegalArgumentException("Scenario ID cannot be null or 0.");
        }
    }

    public int getTurnNumber() {
        return value;
    }
}
