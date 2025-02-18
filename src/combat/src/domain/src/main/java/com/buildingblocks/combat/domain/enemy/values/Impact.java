package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Impact  implements IValueObject {
    private final int impact
            ;

    private Impact(int impact) {
        this.impact = impact;
    }

    public static Impact of(int impact) {
        return new Impact(impact);
    }

    @Override
    public void validate() {
        if (impact ==0) {
            throw new IllegalArgumentException("El impacto del efecto no puede ser nulo o vacío.");
        }
    }

    public int getImpact() {
        return impact;
    }
}
