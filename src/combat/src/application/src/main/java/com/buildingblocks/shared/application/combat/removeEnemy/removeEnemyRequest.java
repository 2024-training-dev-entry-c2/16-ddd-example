package com.buildingblocks.shared.application.combat.removeEnemy;

import com.buildingblocks.shared.application.Request;

public class removeEnemyRequest extends Request {
    private final String enemyId;

    public removeEnemyRequest(String aggregateId, String enemyId) {
        super(aggregateId);
        this.enemyId = enemyId;
    }

    public String getEnemyId() {
        return enemyId;
    }
}
