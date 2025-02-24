package com.buildingblocks.shared.application.combat.domain.deckOfCards.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;

public class Enhanced  implements IValueObject {
    private final Boolean value;

    private Enhanced(Boolean value) {
        this.value = value;
    }

    public static Enhanced of(Boolean value) {
        return new Enhanced(value);
    }

    @Override
    public void validate() {

    }

    public Boolean isValue() {
        return value;
    }
}
