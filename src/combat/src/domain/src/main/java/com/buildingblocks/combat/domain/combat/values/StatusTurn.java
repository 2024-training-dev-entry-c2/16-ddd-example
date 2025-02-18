package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class StatusTurn implements IValueObject {
    private final String value;

    private StatusTurn(String value) {
        this.value = value;
    }

    public static StatusTurn of(String value) {
        StatusTurn name = new StatusTurn(value);
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
