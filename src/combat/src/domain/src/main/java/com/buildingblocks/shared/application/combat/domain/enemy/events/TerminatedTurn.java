package com.buildingblocks.shared.application.combat.domain.enemy.events;

import com.buildingblocks.shared.application.combat.domain.character.events.EventsEnum;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class TerminatedTurn extends DomainEvent {
    private final String enemyId;

    public TerminatedTurn( String characterId) {
        super(EventsEnum.TERMINATED_TURN.name());
        this.enemyId = characterId;
    }

    public String getEnemyId() {
        return enemyId;
    }
}
