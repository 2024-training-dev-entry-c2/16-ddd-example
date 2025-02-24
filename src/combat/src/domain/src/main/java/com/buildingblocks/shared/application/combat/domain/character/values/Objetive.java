package com.buildingblocks.shared.application.combat.domain.character.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

public class Objetive implements IValueObject {
    private final Integer value;

    private Objetive(Integer value) {

        this.value = value;
        validate();
    }
    public static Objetive of(Integer value) {
        Objetive result = new Objetive(value);
        result.validate();
        return result;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public void validate() {
        Validator.validatePositive(value);
    }
}