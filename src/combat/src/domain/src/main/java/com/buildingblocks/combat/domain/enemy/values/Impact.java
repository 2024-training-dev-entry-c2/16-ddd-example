package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.Validator;

public class Impact  implements IValueObject {
    private final Integer impact
            ;

    private Impact(Integer impact) {
        this.impact = impact;
    }

    public static Impact of(Integer impact) {
        return new Impact(impact);
    }

    @Override
    public void validate() {
        Validator.validatePositive(impact);
    }

    public Integer getImpact() {
        return impact;
    }
}
