package com.buildingblocks.shared.application.combat.domain.character.values;

import com.buildingblocks.shared.application.shared.domain.generic.Identity;

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
