package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class EnemyId extends Identity {
    public EnemyId() {
        super();
    }
    public EnemyId(String value) {
        super(value);
    }
    public static EnemyId of(String value) {
        return new EnemyId(value);
    }
}
