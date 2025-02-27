package com.buildingblocks.industries.application.player.addplayer;

import com.buildingblocks.shared.application.Request;

public class AddPlayerRequest extends Request {
    private Integer budget;
    private Integer income;

    public AddPlayerRequest(Integer budget, Integer income) {
        super(null);
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
