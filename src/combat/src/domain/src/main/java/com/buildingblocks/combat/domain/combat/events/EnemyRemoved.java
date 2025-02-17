package com.buildingblocks.combat.domain.combat.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.List;

public class EnemyRemoved extends DomainEvent {

    private final String combatId;
    private final String enemyId;

    public EnemyRemoved(String combatId, String enemyId) {
        super(EventsEnum.ENEMY_REMOVED.name());
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
