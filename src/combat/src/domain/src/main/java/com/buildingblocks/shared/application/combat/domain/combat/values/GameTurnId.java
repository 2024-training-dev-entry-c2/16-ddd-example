package com.buildingblocks.shared.application.combat.domain.combat.values;

import com.buildingblocks.shared.application.shared.domain.generic.Identity;

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
