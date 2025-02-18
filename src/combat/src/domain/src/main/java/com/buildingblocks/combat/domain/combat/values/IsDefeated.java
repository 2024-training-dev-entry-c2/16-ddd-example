package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class IsDefeated implements IValueObject {
    private final boolean value;

    private IsDefeated(boolean value) {
        this.value = value;
    }

    public static IsDefeated of(boolean value) {
        IsDefeated initiative = new IsDefeated(value);
        initiative.validate();
        return initiative;
    }
    public static IsDefeated defeated(){
        IsDefeated defeated = new IsDefeated(false);
        defeated.validate();
        return defeated;
    }

    @Override
    public void validate() {
        if (value==true) {
            throw new IllegalArgumentException("Initiative value cannot be negative.");
        }
    }

    public boolean getValue() {
        return value;
    }
}
