package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Health implements IValueObject {
    private final int value;

    private Health(int value) {
        this.value = value;
    }

    public static Health of(int value) {
        Health health = new Health(value);
        health.validate();
        return health;
    }

    @Override
    public void validate() {
        if (value < 0) {
            throw new IllegalArgumentException("Health value cannot be negative.");
        }
    }

    public int getValue() {
        return value;
    }
}