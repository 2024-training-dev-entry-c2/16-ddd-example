package com.buildingblocks.industries.application.player.takeloan;

import com.buildingblocks.shared.application.Request;

public class TakeLoanRequest extends Request {
    private String id;
    private Integer amount;
    private Integer reductionbudget;
    private Integer updatedBudget;

    public TakeLoanRequest() {
        super(null);
    }

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

    public void setId(String id) {
        this.id = id;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setReductionbudget(Integer reductionbudget) {
        this.reductionbudget = reductionbudget;
    }

    public void setUpdatedBudget(Integer updatedBudget) {
        this.updatedBudget = updatedBudget;
    }
}
