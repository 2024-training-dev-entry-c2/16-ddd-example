package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class Location implements IValueObject {
    private final String value;

    private Location(String value) {
        this.value = value;
        validate();
    }

    public static Location of(String value) {
        return new Location(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateStringNotEmpty(value, "Location");
        ValueValidator.validateStringNotBlank(value, "Location");
        ValueValidator.validateSpecialCharacters(value, "Location");
    }

    public String getValue() {
        return value;
    }
}
