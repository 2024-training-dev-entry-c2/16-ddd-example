package com.buildingblocks.shared.application.combat.domain.combat.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

public class Initiative implements IValueObject {
    private final Integer value;

    private Initiative(Integer value) {
        this.value = value;
    }

    public static Initiative of(Integer value) {
        Initiative initiative = new Initiative(value);
        initiative.validate();
        return initiative;
    }

    @Override
    public void validate() {
        Validator.validatePositive(value);

    }

    public Integer getValue() {
        return value;
    }
}
