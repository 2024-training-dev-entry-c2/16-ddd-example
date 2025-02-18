package com.buildingblocks.industries.domain.industry.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class Level implements IValueObject {
    private final Integer value;

    private Level(Integer value) {
        this.value = value;
        validate();
    }

    public static Level of(Integer value) {
        return new Level(value);
    }

    @Override
    public void validate() {
        ValueValidator.validateNotNull(value, "Level");
        ValueValidator.validatePositive(value, "Level");
    }

    public Integer getValue() {
        return value;
    }
}
