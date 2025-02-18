package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.Validator;

public class Name implements IValueObject {
    private final String value;

    private Name(String value) {
        this.value = value;
    }

    public static Name of(String value) {
        Name name = new Name(value);
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
