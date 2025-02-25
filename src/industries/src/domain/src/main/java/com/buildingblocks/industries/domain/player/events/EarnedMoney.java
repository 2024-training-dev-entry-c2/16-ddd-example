package com.buildingblocks.industries.domain.player.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class EarnedMoney extends DomainEvent {
    private String id;
    private Integer amount;
    private Integer newBudget;

    public EarnedMoney() {
        super(EventsEnum.EARNED_MONEY.name());
    }

    public EarnedMoney(String id, Integer amount, Integer newBudget) {
        super(EventsEnum.EARNED_MONEY.name());
        this.id = id;
        this.amount = amount;
        this.newBudget = newBudget;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setNewBudget(Integer newBudget) {
        this.newBudget = newBudget;
    }
}
