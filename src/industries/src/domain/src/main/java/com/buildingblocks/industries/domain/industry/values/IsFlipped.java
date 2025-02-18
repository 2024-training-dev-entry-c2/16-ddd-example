package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class IsFlipped implements IValueObject {
    private final Boolean value;

    private IsFlipped(Boolean value) {
        this.value = value;
        validate();
    }

    public static IsFlipped of(Boolean value) {
        return new IsFlipped(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateBooleanNotNull(value, "IsFlipped");
    }

    public Boolean getValue() {
        return value;
    }
}
