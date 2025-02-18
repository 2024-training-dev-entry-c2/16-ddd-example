package com.buildingblocks.combat.domain.deckOfCards.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Enhanced  implements IValueObject {
    private final boolean value;

    private Enhanced(boolean value) {
        this.value = value;
    }

    public static Enhanced of(boolean value) {
        return new Enhanced(value);
    }

    @Override
    public void validate() {

    }

    public boolean isValue() {
        return value;
    }
}
