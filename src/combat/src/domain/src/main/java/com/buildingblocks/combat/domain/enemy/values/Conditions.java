package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Conditions implements IValueObject {
    private final String value;

    public Conditions(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del personaje no puede estar vacío.");
        }
        this.value = value;
    }
    public static Conditions of(String value) {
        return new Conditions(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public void validate() {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del personaje no puede estar vacío.");
        }
    }
}
