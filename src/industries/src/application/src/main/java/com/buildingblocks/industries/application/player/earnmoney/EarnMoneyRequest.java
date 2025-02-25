package com.buildingblocks.industries.application.player.earnmoney;

import com.buildingblocks.shared.application.Request;

public class EarnMoneyRequest extends Request {
    private String id;
    private Integer amount;
    private Integer newBudget;

    public EarnMoneyRequest() {
        super(null);
    }

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
