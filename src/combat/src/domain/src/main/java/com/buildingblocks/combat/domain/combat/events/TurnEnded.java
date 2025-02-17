package com.buildingblocks.combat.domain.combat.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.Date;

public class TurnEnded extends DomainEvent {
    private final int NumTurn;
    private final Date endDate;

    public TurnEnded(String type, int numTurn, Date endDate) {
        super(type);
        NumTurn = numTurn;
        this.endDate = endDate;
    }

    public int getNumTurn() {
        return NumTurn;
    }

    public Date getEndDate() {
        return endDate;
    }
}
