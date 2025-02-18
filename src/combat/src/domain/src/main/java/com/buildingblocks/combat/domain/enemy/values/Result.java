package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Result implements IValueObject {
    private final String value;

    private Result(String value) {
        this.value = value;
    }

    public static Result of(String value) {
        Result result = new Result(value);
        result.validate();
        return result;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void validate() {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El resultado no puede estar vacío.");
        }
    }
}
