package com.buildingblocks.combat.domain.character.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class level  implements IValueObject {
    private final int value;

    public level(int value) {
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

    public static level of(int value) {
        return new level(value);
    }
}
