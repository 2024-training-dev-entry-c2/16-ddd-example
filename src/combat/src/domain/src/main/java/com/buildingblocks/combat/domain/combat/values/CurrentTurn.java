package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.Validator;

public class CurrentTurn implements IValueObject {
    private final Integer value;

    private CurrentTurn(Integer id) {
        this.value = id;
    }

    public static CurrentTurn of(Integer id) {
        CurrentTurn scenarioId = new CurrentTurn(id);
        scenarioId.validate();
        return scenarioId;
    }

    @Override
    public void validate() {
        Validator.validatePositive(value);
    }

    public Integer getTurnNumber() {
        return value;
    }
}
