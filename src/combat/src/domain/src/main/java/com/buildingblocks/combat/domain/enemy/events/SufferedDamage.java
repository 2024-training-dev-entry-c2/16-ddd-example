package com.buildingblocks.combat.domain.enemy.events;

import com.buildingblocks.combat.domain.character.events.EventsEnum;
import com.buildingblocks.shared.domain.generic.DomainEvent;

public class SufferedDamage extends DomainEvent {
    private final String enemyId;
    private final int amountDamage;

    public SufferedDamage(String characterId, int amountDamage) {
        super(EventsEnum.DAMAGE_SUFFERED.name());
        this.enemyId = characterId;
        this.amountDamage = amountDamage;
    }

    public String getCharacterId() {
        return enemyId;
    }

    public int getAmountDamage() {
        return amountDamage;
    }
}
