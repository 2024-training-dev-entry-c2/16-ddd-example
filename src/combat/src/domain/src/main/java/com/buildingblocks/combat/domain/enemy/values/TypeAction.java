package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.Validator;

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
        Validator.validateTextNotNull(value);

    }

    public String getValue() {
        return value;
    }
}
