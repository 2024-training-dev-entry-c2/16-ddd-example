package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class TechLevelRequired implements IValueObject {
    private final Integer value;

    private TechLevelRequired(Integer value) {
        this.value = value;
        validate();
    }

    public static TechLevelRequired of(Integer value) {
        return new TechLevelRequired(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateNotNull(value, "TechLevelRequired");
        ValueValidator.validatePositive(value, "TechLevelRequired");
    }

    public Integer getValue() {
        return value;
    }
}
