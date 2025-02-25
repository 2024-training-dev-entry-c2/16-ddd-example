package com.buildingblocks.shared.application.combat.domain.combat.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

import java.util.Date;

public class TurnEnded extends DomainEvent {
    private Integer NumTurn;
    private Date endDate;

    public TurnEnded() {
        super(EventsEnum.SHIFT_ENDED.name());
    }

    public TurnEnded(String type, Integer numTurn, Date endDate) {
        super(EventsEnum.SHIFT_ENDED.name());
        NumTurn = numTurn;
        this.endDate = endDate;
    }

    public Integer getNumTurn() {
        return NumTurn;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setNumTurn(Integer numTurn) {
        NumTurn = numTurn;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
