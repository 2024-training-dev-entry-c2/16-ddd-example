package com.buildingblocks.industries.domain.player.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class AdjustedIncome extends DomainEvent {
    private String id;
    private Integer changedIncome;
    private String reason;
    private Integer updatedIncome;

    public AdjustedIncome() {
        super(EventsEnum.ADJUSTED_INCOME.name());
    }

    public AdjustedIncome(String id, Integer changedIncome, String reason, Integer updatedIncome) {
        super(EventsEnum.ADJUSTED_INCOME.name());
        this.id = id;
        this.changedIncome = changedIncome;
        this.reason = reason;
        this.updatedIncome = updatedIncome;
    }

    public String getId() {
        return id;
    }

    public Integer getChangedIncome() {
        return changedIncome;
    }

    public String getReason() {
        return reason;
    }

    public Integer getUpdatedIncome() {
        return updatedIncome;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setChangedIncome(Integer changedIncome) {
        this.changedIncome = changedIncome;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setUpdatedIncome(Integer updatedIncome) {
        this.updatedIncome = updatedIncome;
    }
}
