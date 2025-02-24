package com.buildingblocks.shared.application.combat.domain.character.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;

public class TypeObject implements IValueObject {
    private final String value;

    private TypeObject(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de objeto no puede estar vacío.");
        }
        this.value = value;
    }
    public static TypeObject of(String value) {
        TypeObject object = new TypeObject(value);
        object.validate();
        return object;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void validate() {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de objeto no puede estar vacío.");
        }
    }
}
