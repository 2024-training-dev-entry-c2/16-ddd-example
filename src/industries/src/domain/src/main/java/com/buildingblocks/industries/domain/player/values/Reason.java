package com.buildingblocks.industries.domain.player.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class Reason implements IValueObject {
    private final String value;

    private Reason(String value) {
        this.value = value;
        validate();
    }

    public static Reason of(String value) {
        return new Reason(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateSpecialCharacters(value, "Reason");
        ValueValidator.validateStringNotBlank(value, "Reason");
        ValueValidator.validateStringNotEmpty(value, "Reason");
    }

    public String getValue() {
        return value;
    }
}
