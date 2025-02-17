package com.buildingblocks.combat.domain.deckOfCards.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class StatusDeck implements IValueObject {
    private final String value;

    private StatusDeck(String value) {
        this.value = value;
    }

    public static StatusDeck of(String value) {
        return new StatusDeck(value);
    }

    @Override
    public void validate() {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("StatusDeck cannot be null or empty.");
        }
    }

    public String getValue() {
        return value;
    }
}
