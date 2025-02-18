package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.Validator;

public class TypeEnemy implements IValueObject {
    private final String value;

    private TypeEnemy(String tipo) {
        this.value = tipo;
    }

    public static TypeEnemy of(String tipo) {
        return new TypeEnemy(tipo);
    }

    @Override
    public void validate() {
        Validator.validateTextNotNull(value);

    }

    public String getValue() {
        return value;
    }
}
