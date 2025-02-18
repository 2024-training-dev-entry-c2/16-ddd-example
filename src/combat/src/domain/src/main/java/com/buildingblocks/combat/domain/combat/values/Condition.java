package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.Validator;

public class Condition implements IValueObject {
    private final String value;

    private Condition(String value) {
        this.value = value;
    }

    public static Condition of(String value) {
        Condition name = new Condition(value);
        name.validate();
        return name;
    }

    @Override
    public void validate() {
        Validator.validateTextNotNull(value);
    }

    public String getValue() {
        return value;
    }
}
