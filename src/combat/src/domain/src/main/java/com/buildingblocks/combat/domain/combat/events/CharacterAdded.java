package com.buildingblocks.combat.domain.combat.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class CharacterAdded extends DomainEvent {
    private final String combatId;
    private final String characterId;
    private final String name;
    private final Integer heal;
    private final Integer initiative;


    public CharacterAdded(String combatId, String characterId, String name, Integer heal, Integer initiative) {
        super(EventsEnum.CHARACTER_ADDED.name());
        this.combatId = combatId;
        this.characterId = characterId;
        this.name = name;
        this.heal = heal;
        this.initiative = initiative;
    }

    public String getCombatId() {
        return combatId;
    }

    public String getCharacterId() {
        return characterId;
    }

    public String getName() {
        return name;
    }

    public Integer getHeal() {
        return heal;
    }

    public Integer getInitiative() {
        return initiative;
    }
}
