package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class Origin implements IValueObject {
    private final String value;

    private Origin(String value) {
        this.value = value;
        validate();
    }

    public static Origin of(String value) {
        return new Origin(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateStringNotEmpty(value, "Origin");
        ValueValidator.validateStringNotBlank(value, "Origin");
        ValueValidator.validateSpecialCharacters(value, "Origin");
    }

    public String getValue() {
        return value;
    }
}
