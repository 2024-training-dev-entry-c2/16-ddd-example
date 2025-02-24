package com.buildingblocks.shared.application.combat.domain.character.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

public class Health implements IValueObject {
    private final Integer value;

    public Health(Integer value) {
        if (value < 0) {
            throw new IllegalArgumentException("La salud no puede ser negativa.");
        }
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public void validate() {
        Validator.validatePositive(value);
    }

    public static Health of(Integer value) {
        return new Health(value);
    }
}
