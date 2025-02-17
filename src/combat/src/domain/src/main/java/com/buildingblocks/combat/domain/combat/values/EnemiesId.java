package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class EnemiesId extends Identity {
    public EnemiesId() {
        super();
    }

    public EnemiesId(String value) {
        super(value);
    }

    public static EnemiesId of(String value) {
        return new EnemiesId(value);
    }
}
