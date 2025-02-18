package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class IsRequiredResearch implements IValueObject {
    private final Boolean value;

    private IsRequiredResearch(Boolean value) {
        this.value = value;
        validate();
    }

    public static IsRequiredResearch of(Boolean value) {
        return new IsRequiredResearch(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateBooleanNotNull(value, "IsRequiredResearch");
    }

    public Boolean getValue() {
        return value;
    }
}
