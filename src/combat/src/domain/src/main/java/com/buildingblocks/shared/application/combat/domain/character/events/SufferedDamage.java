package com.buildingblocks.shared.application.combat.domain.character.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class SufferedDamage extends DomainEvent {
     private final String characterId;
     private final Integer amountDamage;

    public SufferedDamage( String characterId, Integer amountDamage) {
        super(EventsEnum.DAMAGE_SUFFERED.name());
        this.characterId = characterId;
        this.amountDamage = amountDamage;
    }

    public String getCharacterId() {
        return characterId;
    }

    public Integer getAmountDamage() {
        return amountDamage;
    }
}
