package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.Identity;

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
