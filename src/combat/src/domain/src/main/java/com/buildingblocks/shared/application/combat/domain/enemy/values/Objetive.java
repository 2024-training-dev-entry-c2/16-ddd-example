package com.buildingblocks.shared.application.combat.domain.enemy.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

public class Objetive implements IValueObject {
    private final Integer value;

    private Objetive(Integer value) {
        this.value = value;
    }

    public static Objetive of(Integer value) {
        return new Objetive(value);
    }

    @Override
    public void validate() {
        Validator.validatePositive(value);
    }

    public Integer getValue() {
        return value;
    }

}
