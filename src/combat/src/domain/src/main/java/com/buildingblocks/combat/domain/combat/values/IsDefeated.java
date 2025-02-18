package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class IsDefeated implements IValueObject {
    private final Boolean value;

    private IsDefeated(Boolean value) {
        this.value = value;
    }

    public static IsDefeated of(Boolean value) {
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

    }

    public Boolean getValue() {
        return value;
    }
}
