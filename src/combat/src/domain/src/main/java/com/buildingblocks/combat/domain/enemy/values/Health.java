package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Health implements IValueObject {
    private final int value;

    public Health(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("La salud no puede ser negativa.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void validate() {
        if (value < 0) {
            throw new IllegalArgumentException("La salud no puede ser negativa.");
        }
    }

    public static Health of(int value) {
        return new Health(value);
    }
}

