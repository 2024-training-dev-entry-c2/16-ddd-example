package com.buildingblocks.combat.domain.deckOfCards.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class InitiativeCard implements IValueObject {
    private final int value;

    private InitiativeCard(int value) {
        this.value = value;
    }

    public static InitiativeCard of(int value) {
        return new InitiativeCard(value);
    }

    @Override
    public void validate() {
        if (value < 0) {
            throw new IllegalArgumentException("InitiativeCard cannot be negative.");
        }
    }

    public int getValue() {
        return value;
    }
}
