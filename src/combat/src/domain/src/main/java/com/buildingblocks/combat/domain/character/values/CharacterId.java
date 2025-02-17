package com.buildingblocks.combat.domain.character.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class CharacterId extends Identity {

    public CharacterId() {
        super();
    }

    public CharacterId(String value) {
        super(value);
    }

    public static CharacterId of(String value) {
        return new CharacterId(value);
    }
}
