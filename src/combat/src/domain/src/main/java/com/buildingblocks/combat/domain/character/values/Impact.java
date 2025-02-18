package com.buildingblocks.combat.domain.character.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.Validator;

public class Impact implements IValueObject {
    private final Integer value;

    public Impact(Integer value) {
        this.value = value;
        validate();
    }
    public static Impact of(Integer value) {
        Impact impact = new Impact(value);
        impact.validate();
        return impact;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public void validate() {
        Validator.validatePositive(value);
    }
}
