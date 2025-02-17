package com.buildingblocks.combat.domain.combat.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.Date;

public class TurnStarted extends DomainEvent {
    private final int NumTurn;
    private final Date startDate;

    public TurnStarted(String type, int numTurn, Date startDate) {
        super(EventsEnum.SHIFT_STARTED.name());
        NumTurn = numTurn;
        this.startDate = startDate;
    }

    public int getNumTurn() {
        return NumTurn;
    }

    public Date getStartDate() {
        return startDate;
    }
}
