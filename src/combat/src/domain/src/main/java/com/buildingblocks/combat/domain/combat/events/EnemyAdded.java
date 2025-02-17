package com.buildingblocks.combat.domain.combat.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class EnemyAdded extends DomainEvent {
    private final String combatId;
    private final String enemyId;

    public EnemyAdded(String combatId, String enemyId) {
        super(EventsEnum.ENEMY_ADDED.name());
        this.combatId = combatId;
        this.enemyId = enemyId;
    }

    public String getCombatId() {
        return combatId;
    }

    public String getEnemyId() {
        return enemyId;
    }
}
