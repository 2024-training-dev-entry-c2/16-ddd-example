package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class Destination implements IValueObject {
    private final String value;

    private Destination(String value) {
        this.value = value;
        validate();
    }

    public static Destination of(String value) {
        return new Destination(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateStringNotEmpty(value, "Destination");
        ValueValidator.validateStringNotBlank(value, "Destination");
        ValueValidator.validateSpecialCharacters(value, "Destination");
    }

    public String getValue() {
        return value;
    }
}
