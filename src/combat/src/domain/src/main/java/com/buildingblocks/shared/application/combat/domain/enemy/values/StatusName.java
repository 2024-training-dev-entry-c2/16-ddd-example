package com.buildingblocks.shared.application.combat.domain.enemy.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

public class StatusName implements IValueObject {
    private final String value;

    private StatusName(String tipo) {
        this.value = tipo;
    }

    public static StatusName of(String tipo) {
        return new StatusName(tipo);
    }
    @Override
    public void validate() {
        Validator.validateTextNotNull(value);

    }

    public String getValue() {
        return value;
    }
}
