package com.buildingblocks.industries.domain.player.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class Quantity implements IValueObject {
    private final Integer value;

    private Quantity(Integer value) {
        this.value = value;
        validate();
    }

    public static Quantity of(Integer value) {
        return new Quantity(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateNotNull(value, "Quantity");
        ValueValidator.validateNonNegative(value, "Quantity");
    }

    public Integer getValue() {
        return value;
    }
}
