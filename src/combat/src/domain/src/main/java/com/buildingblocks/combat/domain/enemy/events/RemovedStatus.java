package com.buildingblocks.combat.domain.enemy.events;

import com.buildingblocks.combat.domain.character.events.EventsEnum;
import com.buildingblocks.shared.domain.generic.DomainEvent;

public class RemovedStatus extends DomainEvent {
    private final String enemyId;
    private final String effectId;

    public RemovedStatus(String characterId, String effectId) {
        super(EventsEnum.STATE_REMOVED.name());
        this.enemyId = characterId;
        this.effectId = effectId;
    }

    public String getEnemyId() {
        return enemyId;
    }

    public String getEffectId() {
        return effectId;
    }
}
