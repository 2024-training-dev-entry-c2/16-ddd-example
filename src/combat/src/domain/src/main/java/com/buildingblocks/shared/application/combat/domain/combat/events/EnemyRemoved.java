package com.buildingblocks.shared.application.combat.domain.combat.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class EnemyRemoved extends DomainEvent {


    private  String enemyId;
    public EnemyRemoved() {
        super(EventsEnum.ENEMY_REMOVED.name());
    }
    public EnemyRemoved( String enemyId) {
        super(EventsEnum.ENEMY_REMOVED.name());
        this.enemyId = enemyId;
    }
        public void setEnemyId(String enemyId) {
        this.enemyId = enemyId;
    }


    public String getEnemyId() {
        return enemyId;
    }
}
