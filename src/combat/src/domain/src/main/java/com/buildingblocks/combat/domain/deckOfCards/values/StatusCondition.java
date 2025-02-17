package com.buildingblocks.combat.domain.deckOfCards.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class StatusCondition implements IValueObject {
    private final String value;

    private StatusCondition(String value) {
        this.value = value;
    }

    public static StatusCondition of(String value) {
        return new StatusCondition(value);
    }

    @Override
    public void validate() {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("CondicionDeEstado cannot be null or empty.");
        }
    }

    public String getValue() {
        return value;
    }
}
