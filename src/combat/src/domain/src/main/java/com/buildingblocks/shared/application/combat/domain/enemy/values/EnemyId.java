package com.buildingblocks.shared.application.combat.domain.enemy.values;

import com.buildingblocks.shared.application.shared.domain.generic.Identity;

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
