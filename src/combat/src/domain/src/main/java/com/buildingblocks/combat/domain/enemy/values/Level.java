package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Level implements IValueObject {
    private final int value;

    public Level(int value) {
        if (value < 1) {
            throw new IllegalArgumentException("El nivel no puede ser menor que 1.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void validate() {
        if (value < 1) {
            throw new IllegalArgumentException("El nivel no puede ser menor que 1.");
        }
    }

    public static Level of(int value) {
        return new Level(value);
    }
}
