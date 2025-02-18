package com.buildingblocks.industries.domain.resourceMarket.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class ResourceQuantity implements IValueObject {
    private final Integer value;

    private ResourceQuantity(Integer value) {
        this.value = value;
        validate();
    }

    public static ResourceQuantity of(Integer value) {
        return new ResourceQuantity(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateNotNull(value, "ResourceQuantity");
        ValueValidator.validateNonNegative(value, "ResourceQuantity");
    }

    public Integer getValue() {
        return value;
    }
}
