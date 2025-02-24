package com.buildingblocks.shared.application.combat.domain.deckOfCards.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

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
        Validator.validateTextNotNull(value);
    }

    public String getValue() {
        return value;
    }
}
