package com.buildingblocks.combat.domain.combat.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class EnemyAdded extends DomainEvent {
    private final String combatId;
    private final String enemyId;
    private final String name;
    private final Integer heal;
    private final Integer initiative;

    public EnemyAdded( String combatId, String enemyId, String name, Integer heal, Integer initiative) {
        super(EventsEnum.ENEMY_ADDED.name());
        this.combatId = combatId;
        this.enemyId = enemyId;
        this.name = name;
        this.heal = heal;
        this.initiative = initiative;
    }

    public String getCombatId() {
        return combatId;
    }

    public String getEnemyId() {
        return enemyId;
    }

    public String getName() {
        return name;
    }

    public Integer getHeal() {
        return heal;
    }

    public Integer getInitiative() {
        return initiative;
    }
}
