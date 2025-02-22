package com.buildingblocks.industries.application.player.earnmoney;

import com.buildingblocks.shared.application.Request;

public class EarnMoneyRequest extends Request {
    private final String id;
    private final Integer amount;
    private final Integer newBudget;

    public EarnMoneyRequest(String aggregateId, String id, Integer amount, Integer newBudget) {
        super(aggregateId);
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
