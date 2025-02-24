package com.buildingblocks.shared.application.combat.domain.character.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class EquippedObject extends DomainEvent {
    private final String characterId;
    private final String objectId;
    private final String objectName;

    public EquippedObject(String characterId, String objectId, String objectName) {
        super(EventsEnum.EQUIPPED_OBJECT.name());
        this.characterId = characterId;
        this.objectId = objectId;
        this.objectName = objectName;
    }

    public String getCharacterId() {
        return characterId;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getObjectName() {
        return objectName;
    }
}
