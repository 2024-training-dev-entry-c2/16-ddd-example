package com.buildingblocks.combat.domain.combat.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.Date;
import java.util.List;

public class CharacterRemoved extends DomainEvent {
    private final String combatId;
    private final String characterId;

    public CharacterRemoved(String combatId, String characterId) {
        super(EventsEnum.CHARACTER_REMOVED.name());
        this.combatId = combatId;
        this.characterId = characterId;
    }

    public String getCombatId() {
        return combatId;
    }

    public String getCharacterId() {
        return characterId;
    }

}
