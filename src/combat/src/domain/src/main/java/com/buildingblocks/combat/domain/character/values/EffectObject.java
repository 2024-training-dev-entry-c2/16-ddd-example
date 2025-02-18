package com.buildingblocks.combat.domain.character.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.Validator;

public class EffectObject implements IValueObject {
    private final String value;

    public EffectObject(String value) {
        this.value = value;
    }
    public static EffectObject of(String value) {
        EffectObject effectObject =  new EffectObject(value);
        effectObject.validate();
        return effectObject;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void validate() {
        Validator.validateTextNotNull(value);
    }
}
