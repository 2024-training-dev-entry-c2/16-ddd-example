package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class QuantityRequiredResource implements IValueObject {
    private final Integer value;

    private QuantityRequiredResource(Integer value) {
        this.value = value;
        validate();
    }

    public static QuantityRequiredResource of(Integer value) {
        return new QuantityRequiredResource(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateNotNull(value, "QuantityRequiredResource");
        ValueValidator.validateNonNegative(value, "QuantityRequiredResource");
    }

    public Integer getValue() {
        return value;
    }
}
