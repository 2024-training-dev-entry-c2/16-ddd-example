package com.buildingblocks.shared.application.combat.domain.enemy.values;

import com.buildingblocks.shared.application.shared.domain.generic.Identity;

public class HistoricalActionsId extends Identity {
    public HistoricalActionsId() {
        super();
    }

    public HistoricalActionsId(String value) {
        super(value);
    }
    public static HistoricalActionsId of(String value) {
        return new HistoricalActionsId(value);
    }
}
