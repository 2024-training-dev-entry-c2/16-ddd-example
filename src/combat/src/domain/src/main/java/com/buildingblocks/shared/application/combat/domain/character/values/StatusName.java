package com.buildingblocks.shared.application.combat.domain.character.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

public class StatusName implements IValueObject {
    private final String value;
    private StatusName(String value) {
        this.value = value;
    }

    public static StatusName of(String value) {
        StatusName statusName = new StatusName(value);
        statusName.validate();
        return statusName;
    }
    public String getValue() {
        return value;
    }

    @Override
    public void validate() {
        Validator.validateTextNotNull(value);
    }
}
