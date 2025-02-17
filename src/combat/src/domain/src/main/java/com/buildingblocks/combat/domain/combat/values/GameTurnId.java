package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class GameTurnId extends Identity {
    public GameTurnId() {
        super();
    }

    private GameTurnId(String value) {
        super(value);
    }

    public static GameTurnId of(String value) {
        return new GameTurnId(value);
    }
}
