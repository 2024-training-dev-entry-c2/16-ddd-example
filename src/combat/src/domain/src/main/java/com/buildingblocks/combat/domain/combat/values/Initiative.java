package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Initiative implements IValueObject {
    private final int value;

    private Initiative(int value) {
        this.value = value;
    }

    public static Initiative of(int value) {
        Initiative initiative = new Initiative(value);
        initiative.validate();
        return initiative;
    }

    @Override
    public void validate() {
        if (value < 0) {
            throw new IllegalArgumentException("Initiative value cannot be negative.");
        }
    }

    public int getValue() {
        return value;
    }
}
