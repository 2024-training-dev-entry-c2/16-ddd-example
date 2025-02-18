package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.combat.domain.deckOfCards.values.Objetives;
import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.Validator;

public class Objetive implements IValueObject {
    private final Integer value;

    private Objetive(Integer value) {
        this.value = value;
    }

    public static Objetive of(Integer value) {
        return new Objetive(value);
    }

    @Override
    public void validate() {
        Validator.validatePositive(value);
    }

    public Integer getValue() {
        return value;
    }

}
