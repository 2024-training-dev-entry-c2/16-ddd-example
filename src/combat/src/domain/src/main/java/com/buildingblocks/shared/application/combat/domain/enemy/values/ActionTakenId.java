package com.buildingblocks.shared.application.combat.domain.enemy.values;

import com.buildingblocks.shared.application.shared.domain.generic.Identity;

public class ActionTakenId extends Identity {
    public ActionTakenId() {
        super();
    }
    public ActionTakenId(String value) {
        super(value);
    }
    public static ActionTakenId of(String value) {
        return new ActionTakenId(value);
    }
}
