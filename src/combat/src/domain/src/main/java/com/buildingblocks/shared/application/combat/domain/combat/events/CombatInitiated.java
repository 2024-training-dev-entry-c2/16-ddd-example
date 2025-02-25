package com.buildingblocks.shared.application.combat.domain.combat.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

import java.util.Date;

public class CombatInitiated extends DomainEvent {
    private  String scenarioId;
    private  Date startDate;
    public CombatInitiated() {
        super(EventsEnum.COMBAT_INITIATED.name());
    }
    public CombatInitiated( String scenarioId, Date dateStart) {
        super(EventsEnum.COMBAT_INITIATED.name());
        this.scenarioId = scenarioId;
        this.startDate = dateStart;
    }
    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public void setDateStart(Date dateStart) {
        this.startDate = dateStart;
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public Date getDateStart() {
        return startDate;
    }
}
