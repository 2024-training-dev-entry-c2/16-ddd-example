package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.Identity;

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
