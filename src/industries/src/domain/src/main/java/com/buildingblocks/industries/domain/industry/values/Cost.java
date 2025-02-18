package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class Cost implements IValueObject {
    private final Integer value;

    private Cost(Integer value) {
        this.value = value;
        validate();
    }

    public static Cost of(Integer value) {
        return new Cost(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateNotNull(value, "Cost");
        ValueValidator.validatePositive(value, "Cost");
    }

    public Integer getValue() {
        return value;
    }
}
