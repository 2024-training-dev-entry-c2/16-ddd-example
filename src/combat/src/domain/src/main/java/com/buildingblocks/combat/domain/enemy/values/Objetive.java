package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.combat.domain.deckOfCards.values.Objetives;
import com.buildingblocks.shared.domain.generic.IValueObject;

public class Objetive implements IValueObject {
    private final int value;

    private Objetive(int value) {
        this.value = value;
    }

    public static Objetive of(int value) {
        return new Objetive(value);
    }

    @Override
    public void validate() {
        if (value == 0 ) {
            throw new IllegalArgumentException("Objetivos cannot be null or empty.");
        }
    }

    public int getValue() {
        return value;
    }

}
