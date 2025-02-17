package com.buildingblocks.combat.domain.deckOfCards.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Scope implements IValueObject {
    private final int value;

    private Scope(int value) {
        this.value = value;
    }

    public static Scope of(int value) {
        return new Scope(value);
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
