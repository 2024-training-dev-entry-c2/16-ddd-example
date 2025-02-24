package com.buildingblocks.shared.application.combat.domain.character.values;

import com.buildingblocks.shared.application.shared.domain.generic.Identity;

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
