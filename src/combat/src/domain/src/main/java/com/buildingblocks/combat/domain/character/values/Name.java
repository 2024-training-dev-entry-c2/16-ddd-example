package com.buildingblocks.combat.domain.character.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.Validator;

public class Name implements IValueObject {
    private final String value;

    private Name(String value) {

        this.value = value;
    }
    public static Name of(String value) {
        Name result = new Name(value);
        result.validate();
        return result;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void validate() {
        Validator.validateTextNotNull(value);
    }
}
