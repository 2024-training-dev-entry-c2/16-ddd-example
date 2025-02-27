package com.buildingblocks.industries.domain.player.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class AddedPlayer extends DomainEvent {
    private Integer budget;
    private Integer income;

    public AddedPlayer() {
        super(EventsEnum.ADDED_PLAYER.name());
    }

    public AddedPlayer(Integer budget, Integer income) {
        super(EventsEnum.ADDED_PLAYER.name());
        this.budget = budget;
        this.income = income;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }
}
