package com.buildingblocks.combat.domain.combat.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.Date;

public class CombatInitiated extends DomainEvent {
    private final String scenarioId;
    private final Date startDate;

    public CombatInitiated( String scenarioId, Date dateStart) {
        super(EventsEnum.COMBAT_INITIATED.name());
        this.scenarioId = scenarioId;
        this.startDate = dateStart;
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public Date getDateStart() {
        return startDate;
    }
}
