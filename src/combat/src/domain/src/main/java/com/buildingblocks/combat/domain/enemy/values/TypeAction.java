package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class TypeAction implements IValueObject {
    private final String value;

    private TypeAction(String tipo) {
        this.value = tipo;
    }

    public static TypeAction of(String tipo) {
        return new TypeAction(tipo);
    }
    @Override
    public void validate() {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de enemigo no puede ser nulo o vacío.");
        }
    }

    public String getValue() {
        return value;
    }
}
