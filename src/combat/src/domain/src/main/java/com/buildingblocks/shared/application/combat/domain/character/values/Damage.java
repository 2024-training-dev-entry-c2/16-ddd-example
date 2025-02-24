package com.buildingblocks.shared.application.combat.domain.character.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

public class Damage  implements IValueObject {
    private final Integer value;

    public Damage(Integer value) {
        this.value = value;
    }

    public static Damage of(Integer value) {
        return new Damage(value);
    }

    @Override
    public void validate() {
        Validator.validatePositive(value);
    }

    public Integer getValue() {
        return value;
    }
}
