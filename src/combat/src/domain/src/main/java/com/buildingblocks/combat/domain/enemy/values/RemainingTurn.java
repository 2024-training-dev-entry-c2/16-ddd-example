package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class RemainingTurn implements IValueObject {
    private final int value;

    private RemainingTurn(int value) {
        this.value = value;
    }

    public static RemainingTurn of(int value) {
        return new RemainingTurn(value);
    }

    @Override
    public void validate() {
        if (value < 0) {
            throw new IllegalArgumentException("Scope cannot be negative.");
        }
    }

    public int getValue() {
        return value;
    }
}

