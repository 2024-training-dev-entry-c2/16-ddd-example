package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class Type implements IValueObject {
    private final String value;

    private Type(String value) {
        this.value = value;
        validate();
    }

    public static Type of(String value) {
        return new Type(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateStringNotEmpty(value, "Type");
        ValueValidator.validateType(value, "Type");
        ValueValidator.validateStringNotBlank(value, "Type");
        ValueValidator.validateSpecialCharacters(value, "Type");
    }

    public String getValue() {
        return value;
    }
}
