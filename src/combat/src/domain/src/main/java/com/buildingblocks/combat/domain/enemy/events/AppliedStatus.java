package com.buildingblocks.combat.domain.enemy.events;

import com.buildingblocks.combat.domain.character.events.EventsEnum;
import com.buildingblocks.shared.domain.generic.DomainEvent;

public class AppliedStatus extends DomainEvent {
    private final String characterId;
    private final String effectId;
    private final String nameEffect;
    private final int impact;
    private final int remainingTurns;

    public AppliedStatus( String characterId, String effectId, String nameEffect, int impact, int remainingTurns) {
        super(EventsEnum.STATE_APPLIED.name());
        this.characterId = characterId;
        this.effectId = effectId;
        this.nameEffect = nameEffect;
        this.impact = impact;
        this.remainingTurns = remainingTurns;
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

    public int getImpact() {
        return impact;
    }
}
