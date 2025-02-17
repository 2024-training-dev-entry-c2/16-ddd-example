package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class CombatId extends Identity {
    public CombatId() {
        super();
    }

    private CombatId(String value) {
        super(value);
    }

    public static CombatId of(String value) {
        return new CombatId(value);
    }
}
