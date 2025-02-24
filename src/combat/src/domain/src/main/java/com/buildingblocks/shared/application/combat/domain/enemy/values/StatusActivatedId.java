package com.buildingblocks.shared.application.combat.domain.enemy.values;

import com.buildingblocks.shared.application.shared.domain.generic.Identity;

public class StatusActivatedId extends Identity {
    public StatusActivatedId() {
        super();
    }
    public StatusActivatedId(String value) {
        super(value);
    }
    public static StatusActivatedId of(String value) {
        return new StatusActivatedId(value);
    }
}
