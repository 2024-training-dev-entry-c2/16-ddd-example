package com.buildingblocks.combat.domain.combat.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.Date;

public class TurnEnded extends DomainEvent {
    private final Integer NumTurn;
    private final Date endDate;

    public TurnEnded(String type, Integer numTurn, Date endDate) {
        super(type);
        NumTurn = numTurn;
        this.endDate = endDate;
    }

    public Integer getNumTurn() {
        return NumTurn;
    }

    public Date getEndDate() {
        return endDate;
    }
}
