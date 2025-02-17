package com.buildingblocks.combat.domain.deckOfCards.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class ParticipantId implements IValueObject {
    private final String value;

    private ParticipantId(String value) {
        this.value = value;
    }

    public static ParticipantId of(String value) {
        return new ParticipantId(value);
    }

    @Override
    public void validate() {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("ParticipantId cannot be null or empty.");
        }
    }

    public String getValue() {
        return value;
    }
}
