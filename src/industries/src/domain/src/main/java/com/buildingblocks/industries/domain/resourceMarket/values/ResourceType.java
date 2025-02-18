package com.buildingblocks.industries.domain.resourceMarket.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class ResourceType implements IValueObject {
    private final String value;

    private ResourceType(String value) {
        this.value = value;
        validate();
    }

    public static ResourceType of(String value) {
        return new ResourceType(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateStringNotEmpty(value, "ResourceType");
        ValueValidator.validateStringNotBlank(value, "ResourceType");
        ValueValidator.validateSpecialCharacters(value, "ResourceType");
        ValueValidator.validateResource(value, "ResourceType");
    }

    public String getValue() {
        return value;
    }
}
