package com.buildingblocks.shared.application.combat.domain.combat.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class CharacterRemoved extends DomainEvent {

    private  String characterId;
    public CharacterRemoved() {
        super(EventsEnum.CHARACTER_REMOVED.name());
    }
    public CharacterRemoved( String characterId) {
        super(EventsEnum.CHARACTER_REMOVED.name());

        this.characterId = characterId;
    }



    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }
}
