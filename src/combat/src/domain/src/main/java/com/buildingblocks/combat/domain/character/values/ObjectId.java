package com.buildingblocks.combat.domain.character.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class ObjectId extends Identity {
    public ObjectId() {
        super();
    }

    public ObjectId(String value) {
        super(value);
    }

    public static ObjectId of(String value) {
        return new ObjectId(value);
    }
}
