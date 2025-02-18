package com.buildingblocks.industries.domain.player.values;

import com.buildingblocks.industries.domain.industry.values.Level;
import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class Budget implements IValueObject {
    private final Integer value;

    private Budget(Integer value) {
        this.value = value;
        validate();
    }

    public static Budget of(Integer value) {
        return new Budget(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateNotNull(value, "Budget");
        ValueValidator.validateNonNegative(value, "Budget");
    }

    public Integer getValue() {
        return value;
    }
}
