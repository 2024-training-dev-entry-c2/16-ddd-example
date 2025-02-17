package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Impact  implements IValueObject {
    private final String impact
            ;

    private Impact(String impact) {
        this.impact = impact;
    }

    public static Impact of(String impact) {
        return new Impact(impact);
    }

    @Override
    public void validate() {
        if (impact == null || impact.trim().isEmpty()) {
            throw new IllegalArgumentException("El impacto del efecto no puede ser nulo o vacío.");
        }
    }

    public String getImpact() {
        return impact;
    }
}
