package com.buildingblocks.combat.domain.enemy.events;

import com.buildingblocks.combat.domain.character.events.EventsEnum;
import com.buildingblocks.shared.domain.generic.DomainEvent;

public class BeCured extends DomainEvent {
    private final String enemyId;
    private final int amountCured;

    public BeCured(String characterId, int amountCured) {
        super(EventsEnum.BE_CURED.name());
        this.enemyId = characterId;
        this.amountCured = amountCured;
    }

    public String getEnemyId() {
        return enemyId;
    }

    public int getAmountCured() {
        return amountCured;
    }
}
