package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class IsConnectedToNetwork implements IValueObject {
    private final Boolean value;

    private IsConnectedToNetwork(Boolean value) {
        this.value = value;
        validate();
    }

    public static IsConnectedToNetwork of(Boolean value) {
        return new IsConnectedToNetwork(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateBooleanNotNull(value, "IsConnectedToNetwork");
    }

    public Boolean getValue() {
        return value;
    }
}
