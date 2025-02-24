package com.buildingblocks.shared.application.combat.domain.enemy.events;

import com.buildingblocks.shared.application.combat.domain.character.events.EventsEnum;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class AppliedStatus extends DomainEvent {
    private final String characterId;
    private final String effectId;
    private final String nameEffect;
    private final Integer impact;
    private final Integer remainingTurns;

    public AppliedStatus( String characterId, String effectId, String nameEffect, Integer impact, Integer remainingTurns) {
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

    public Integer getRemainingTurns() {
        return remainingTurns;
    }

    public Integer getImpact() {
        return impact;
    }
}
