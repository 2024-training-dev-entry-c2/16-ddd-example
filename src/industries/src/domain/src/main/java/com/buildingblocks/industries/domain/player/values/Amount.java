package com.buildingblocks.industries.domain.player.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class Amount implements IValueObject {
    private final Integer value;

    private Amount(Integer value) {
        this.value = value;
        validate();
    }

    public static Amount of(Integer value) {
        return new Amount(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateNotNull(value, "Amount");
        ValueValidator.validateNonNegative(value, "Amount");
    }

    public Integer getValue() {
        return value;
    }
}
