package com.buildingblocks.shared.application.combat.domain.combat.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

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
        Validator.validateTextNotNull(value);

    }

    public String getValue() {
        return value;
    }
}
