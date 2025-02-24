package com.buildingblocks.shared.application.combat.domain.combat.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

import java.util.Date;

public class TurnStarted extends DomainEvent {
    private final Integer NumTurn;
    private final Date startDate;

    public TurnStarted(String type, Integer numTurn, Date startDate) {
        super(EventsEnum.SHIFT_STARTED.name());
        NumTurn = numTurn;
        this.startDate = startDate;
    }

    public Integer getNumTurn() {
        return NumTurn;
    }

    public Date getStartDate() {
        return startDate;
    }
}
