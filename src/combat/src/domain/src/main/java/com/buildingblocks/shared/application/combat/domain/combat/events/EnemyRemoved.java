package com.buildingblocks.shared.application.combat.domain.combat.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class EnemyRemoved extends DomainEvent {


    private final String enemyId;

    public EnemyRemoved( String enemyId) {
        super(EventsEnum.ENEMY_REMOVED.name());
        this.enemyId = enemyId;
    }



    public String getEnemyId() {
        return enemyId;
    }
}
