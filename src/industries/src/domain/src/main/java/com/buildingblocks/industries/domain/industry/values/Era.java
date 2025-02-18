package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class Era implements IValueObject {
    private final String value;

    private Era(String value) {
        this.value = value;
        validate();
    }

    public static Era of(String value) {
        return new Era(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateStringNotEmpty(value, "Era");
        ValueValidator.validateEra(value, "Era");
        ValueValidator.validateStringNotBlank(value, "Era");
        ValueValidator.validateSpecialCharacters(value, "Era");
    }

    public String getValue() {
        return value;
    }
}
