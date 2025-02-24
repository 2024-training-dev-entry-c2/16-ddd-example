package com.buildingblocks.shared.application.combat.addEnemy;

import com.buildingblocks.shared.application.Request;

public class AddEnemyRequest extends Request {
    private final String id;
    private final String name;
    private final Integer health;
    private final Integer initiative;

    public AddEnemyRequest(String aggregateId, String id, String name, Integer health, Integer initiative) {
        super(aggregateId);
        this.id = id;
        this.name = name;
        this.health = health;
        this.initiative = initiative;
    }

    public String getName() {
        return name;
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public String getId() {
        return id;
    }
}
