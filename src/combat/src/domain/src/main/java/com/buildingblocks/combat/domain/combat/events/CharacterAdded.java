package com.buildingblocks.combat.domain.combat.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class CharacterAdded extends DomainEvent {
    private final String combatId;
    private final String characterId;

    public CharacterAdded(String combatId, String characterId) {
        super(EventsEnum.CHARACTER_ADDED.name());
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
