package com.buildingblocks.combat.domain.character.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class TerminatedTurn extends DomainEvent {
    private final String characterId;

    public TerminatedTurn(String type, String characterId) {
        super(EventsEnum.TERMINATED_TURN.name());
        this.characterId = characterId;
    }

    public String getCharacterId() {
        return characterId;
    }
}
