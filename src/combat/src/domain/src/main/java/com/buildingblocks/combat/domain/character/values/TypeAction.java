package com.buildingblocks.combat.domain.character.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.Validator;

public class TypeAction implements IValueObject {
    private final String value;


    private TypeAction(String value) {
        this.value = value;
    }
    public static TypeAction of(String value) {
        TypeAction action = new TypeAction(value);
        action.validate();
        return action;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void validate() {
        Validator.validateTextNotNull(value);
    }
}
