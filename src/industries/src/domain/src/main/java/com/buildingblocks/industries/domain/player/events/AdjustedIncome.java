package com.buildingblocks.industries.domain.player.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class AdjustedIncome extends DomainEvent {
    private final String id;
    private final Integer changedIncome;
    private final String reason;
    private final Integer updatedIncome;

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
}
