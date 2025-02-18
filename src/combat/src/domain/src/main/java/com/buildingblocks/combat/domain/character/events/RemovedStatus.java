package com.buildingblocks.combat.domain.character.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class RemovedStatus extends DomainEvent {
    private final String characterId;
    private final String effectId;

    public RemovedStatus( String characterId, String effectId) {
        super(EventsEnum.STATE_REMOVED.name());
        this.characterId = characterId;
        this.effectId = effectId;
    }

    public String getCharacterId() {
        return characterId;
    }

    public String getEffectId() {
        return effectId;
    }
}
