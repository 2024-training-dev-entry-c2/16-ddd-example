package com.buildingblocks.industries.domain.player.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class SpentBudget extends DomainEvent {
    private String id;
    private Integer amount;
    private Integer newBudget;
    private String reason;

    public SpentBudget() {
        super(EventsEnum.SPENT_BUDGET.name());
    }

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

    public void setId(String id) {
        this.id = id;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setNewBudget(Integer newBudget) {
        this.newBudget = newBudget;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
