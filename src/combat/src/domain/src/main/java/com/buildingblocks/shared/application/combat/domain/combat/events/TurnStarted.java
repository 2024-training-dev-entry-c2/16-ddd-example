package com.buildingblocks.shared.application.combat.domain.combat.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

import java.util.Date;

public class TurnStarted extends DomainEvent {
    private  Integer NumTurn;
    private  Date startDate;
    public TurnStarted() {
        super(EventsEnum.SHIFT_STARTED.name());
    }
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

    public void setNumTurn(Integer numTurn) {
        NumTurn = numTurn;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
