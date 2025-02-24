package com.buildingblocks.shared.application.combat.domain.combat.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class CharacterRemoved extends DomainEvent {

    private final String characterId;

    public CharacterRemoved( String characterId) {
        super(EventsEnum.CHARACTER_REMOVED.name());

        this.characterId = characterId;
    }



    public String getCharacterId() {
        return characterId;
    }

}
