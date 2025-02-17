package com.buildingblocks.combat.domain.character.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class SufferedDamage extends DomainEvent {
     private final String characterId;
     private final int amountDamage;

    public SufferedDamage(String type, String characterId, int amountDamage) {
        super(EventsEnum.DAMAGE_SUFFERED.name());
        this.characterId = characterId;
        this.amountDamage = amountDamage;
    }

    public String getCharacterId() {
        return characterId;
    }

    public int getAmountDamage() {
        return amountDamage;
    }
}
