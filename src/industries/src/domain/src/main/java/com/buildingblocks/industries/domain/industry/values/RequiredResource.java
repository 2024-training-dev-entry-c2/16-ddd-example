package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class RequiredResource implements IValueObject {
    private final String value;

    private RequiredResource(String value) {
        this.value = value;
        validate();
    }

    public static RequiredResource of(String value) {
        return new RequiredResource(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateStringNotEmpty(value, "RequiredResource");
        ValueValidator.validateResource(value, "RequiredResource");
        ValueValidator.validateStringNotBlank(value, "RequiredResource");
        ValueValidator.validateSpecialCharacters(value, "RequiredResource");
    }

    public String getValue() {
        return value;
    }
}
