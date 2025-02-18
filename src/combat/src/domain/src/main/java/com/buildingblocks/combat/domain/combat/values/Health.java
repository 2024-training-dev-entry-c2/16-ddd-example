package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.Validator;

public class Health implements IValueObject {
    private final Integer value;

    private Health(Integer value) {
        this.value = value;
    }

    public static Health of(Integer value) {
        Health health = new Health(value);
        health.validate();
        return health;
    }

    @Override
    public void validate() {
        Validator.validatePositive(value);
    }

    public Integer getValue() {
        return value;
    }
}