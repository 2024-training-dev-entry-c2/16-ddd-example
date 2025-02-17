package com.buildingblocks.combat.domain.character.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class TypeAction implements IValueObject {
    private final String value;

    public TypeAction(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de acción no puede estar vacío.");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void validate() {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de acción no puede estar vacío.");
        }
    }
}
