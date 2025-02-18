package com.buildingblocks.industries.domain.player.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class EarnedMoney extends DomainEvent {
    private final String id;
    private final Integer amount;
    private final Integer newBudget;

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
}
