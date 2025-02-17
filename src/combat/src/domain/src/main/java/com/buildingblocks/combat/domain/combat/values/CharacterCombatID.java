package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class CharacterCombatID  extends Identity {
    public CharacterCombatID() {
        super();
    }

    public CharacterCombatID(String value) {
        super(value);
    }

    public static CharacterCombatID of(String value) {
        return new CharacterCombatID(value);
    }
}
