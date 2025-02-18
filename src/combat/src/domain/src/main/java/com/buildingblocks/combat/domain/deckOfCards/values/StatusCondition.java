package com.buildingblocks.combat.domain.deckOfCards.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.Validator;

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
        Validator.validateTextNotNull(value);
    }

    public String getValue() {
        return value;
    }
}
