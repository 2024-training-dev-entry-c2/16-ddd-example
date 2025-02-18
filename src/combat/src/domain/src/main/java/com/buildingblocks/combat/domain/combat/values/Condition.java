package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Condition implements IValueObject {
    private final String value;

    private Condition(String value) {
        this.value = value;
    }

    public static Condition of(String value) {
        Condition name = new Condition(value);
        name.validate();
        return name;
    }

    @Override
    public void validate() {
        if (value== null || value.isBlank()) {
            throw new IllegalArgumentException("Name value cannot be negative.");
        }
    }

    public String getValue() {
        return value;
    }
}
