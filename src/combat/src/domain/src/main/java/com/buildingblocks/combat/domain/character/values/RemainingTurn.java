package com.buildingblocks.combat.domain.character.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.Validator;

public class RemainingTurn implements IValueObject {
    private final Integer value;

    private RemainingTurn(Integer value) {
        this.value = value;
    }

    public static RemainingTurn of(Integer value) {
        return new RemainingTurn(value);
    }

    @Override
    public void validate() {
        Validator.validatePositive(value);
    }

    public Integer getValue() {
        return value;
    }
}

