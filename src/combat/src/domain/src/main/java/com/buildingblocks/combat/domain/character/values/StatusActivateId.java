package com.buildingblocks.combat.domain.character.values;

import com.buildingblocks.shared.domain.generic.Identity;

public class StatusActivateId extends Identity {
    public StatusActivateId() {
        super();
    }
    public StatusActivateId(String value) {
        super(value);
    }
    public static StatusActivateId of(String value) {
        return new StatusActivateId(value);
    }
}
