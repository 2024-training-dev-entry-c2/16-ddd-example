package com.buildingblocks.industries.application.player.takeloan;

import com.buildingblocks.shared.application.Request;

public class TakeLoanRequest extends Request {
    private final String id;
    private final Integer amount;
    private final Integer reductionbudget;
    private final Integer updatedBudget;

    public TakeLoanRequest(String aggregateId, String id, Integer amount, Integer reductionbudget, Integer updatedBudget) {
        super(aggregateId);
        this.id = id;
        this.amount = amount;
        this.reductionbudget = reductionbudget;
        this.updatedBudget = updatedBudget;
    }

    public String getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getReductionbudget() {
        return reductionbudget;
    }

    public Integer getUpdatedBudget() {
        return updatedBudget;
    }
}
