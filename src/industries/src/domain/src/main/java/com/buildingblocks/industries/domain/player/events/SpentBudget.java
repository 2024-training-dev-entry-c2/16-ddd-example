package com.buildingblocks.industries.domain.player.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class SpentBudget extends DomainEvent {
    private final String id;
    private final Integer amount;
    private final Integer newBudget;
    private final String reason;

    public SpentBudget(String id, Integer amount, Integer newBudget, String reason) {
        super(EventsEnum.SPENT_BUDGET.name());
        this.id = id;
        this.amount = amount;
        this.newBudget = newBudget;
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getNewBudget() {
        return newBudget;
    }

    public String getReason() {
        return reason;
    }
}
