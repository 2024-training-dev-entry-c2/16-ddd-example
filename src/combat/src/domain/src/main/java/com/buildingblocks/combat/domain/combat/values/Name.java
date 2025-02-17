package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Name implements IValueObject {
    private final String value;

    private Name(String value) {
        this.value = value;
    }

    public static Name of(String value) {
        Name name = new Name(value);
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
