package com.buildingblocks.shared.application.combat.removeCharacter;

import com.buildingblocks.shared.application.Request;

public class RemoveCharacterRequest extends Request {
    private final String characterId;

    public RemoveCharacterRequest(String aggregateId, String characterId) {
        super(aggregateId);
        this.characterId = characterId;
    }

    public String getCharacterId() {
        return characterId;
    }
}
