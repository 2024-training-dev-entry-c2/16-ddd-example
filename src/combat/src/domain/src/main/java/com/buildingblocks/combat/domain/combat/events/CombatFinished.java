package com.buildingblocks.combat.domain.combat.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.Date;

public class CombatFinished extends DomainEvent {
    private final String result;
    private final Date dateEnd;

    public CombatFinished(String resultado, Date dateStart) {
        super(EventsEnum.COMBAT_FINISHED.name());
        result = resultado;
        this.dateEnd = dateStart;
    }

    public String getResult() {
        return result;
    }

}
