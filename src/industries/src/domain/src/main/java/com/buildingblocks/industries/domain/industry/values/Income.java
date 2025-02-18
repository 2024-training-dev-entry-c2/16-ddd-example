package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class Income implements IValueObject {
    private final Integer value;

    private Income(Integer value) {
        this.value = value;
        validate();
    }

    public static Income of(Integer value) {
        return new Income(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateNotNull(value, "Income");
        ValueValidator.validateNonNegative(value, "Income");
    }

    public Integer getValue() {
        return value;
    }
}
