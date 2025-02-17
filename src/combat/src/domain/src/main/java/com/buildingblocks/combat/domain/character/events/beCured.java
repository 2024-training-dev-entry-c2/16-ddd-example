package com.buildingblocks.combat.domain.character.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class beCured extends DomainEvent {
    private final String characterId;
    private final int amountCured;

    public beCured(String type, String characterId, int amountCured) {
        super(EventsEnum.BE_CURED.name());
        this.characterId = characterId;
        this.amountCured = amountCured;
    }

    public String getCharacterId() {
        return characterId;
    }

    public int getAmountCured() {
        return amountCured;
    }
}
