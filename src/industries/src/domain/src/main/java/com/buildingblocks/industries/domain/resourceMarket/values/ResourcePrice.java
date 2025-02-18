package com.buildingblocks.industries.domain.resourceMarket.values;

import com.buildingblocks.industries.domain.industry.values.Level;
import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class ResourcePrice implements IValueObject {
    private final Integer value;

    private ResourcePrice(Integer value) {
        this.value = value;
        validate();
    }

    public static ResourcePrice of(Integer value) {
        return new ResourcePrice(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateNotNull(value, "ResourcePrice");
        ValueValidator.validateNonNegative(value, "ResourcePrice");
    }

    public Integer getValue() {
        return value;
    }
}
