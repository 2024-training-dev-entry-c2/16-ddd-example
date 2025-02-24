package com.buildingblocks.shared.application.combat.domain.character.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

public class Level implements IValueObject {
    private final Integer value;

    public Level(Integer value) {
        if (value < 1) {
            throw new IllegalArgumentException("El nivel no puede ser menor que 1.");
        }
        this.value = value;
    }


    public Integer getValue() {
        return value;
    }

    @Override
    public void validate() {
        Validator.validatePositive(value);
    }

    public static Level of(Integer value) {
       Level level = new Level(value);
        level.validate();
        return level;
    }
}
