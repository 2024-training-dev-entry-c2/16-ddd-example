package com.buildingblocks.combat.domain.character.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class AppliedStatus extends DomainEvent {
    private final String characterId;
    private final String effectId;
    private final String nameEffect;
    private final int remainingTurns;

    public AppliedStatus(String type, String characterId, String effectId, String nameEffect, int turnRestans) {
        super(EventsEnum.STATE_APPLIED.name());
        this.characterId = characterId;
        this.effectId = effectId;
        this.nameEffect = nameEffect;
        this.remainingTurns = turnRestans;
    }

    public String getCharacterId() {
        return characterId;
    }

    public String getEffectId() {
        return effectId;
    }

    public String getNameEffect() {
        return nameEffect;
    }

    public int getRemainingTurns() {
        return remainingTurns;
    }
}
