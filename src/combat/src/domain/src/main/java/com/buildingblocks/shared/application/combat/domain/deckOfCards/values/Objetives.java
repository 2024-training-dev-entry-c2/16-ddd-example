package com.buildingblocks.shared.application.combat.domain.deckOfCards.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

public class Objetives implements IValueObject {
    private final Integer value;
    private final Boolean isValidTarget;

    private Objetives(Integer value, Boolean valid) {
        this.value = value;
        this.isValidTarget=valid;
    }

    public static Objetives of(Integer value,Boolean valid) {
        return new Objetives(value,valid);
    }

    @Override
    public void validate() {
        Validator.validatePositive(value);
    }

    public Integer getValue() {
        return value;
    }

    public Boolean isValidTarget() {
        return isValidTarget;
    }
}
