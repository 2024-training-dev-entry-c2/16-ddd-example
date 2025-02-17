package com.buildingblocks.combat.domain.deckOfCards.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

import java.util.List;

public class Objetives implements IValueObject {
    private final int value;
    private final boolean isValidTarget;

    private Objetives(int value, boolean valid) {
        this.value = value;
        this.isValidTarget=valid;
    }

    public static Objetives of(int value,boolean valid) {
        return new Objetives(value,valid);
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

    public boolean isValidTarget() {
        return isValidTarget;
    }
}
