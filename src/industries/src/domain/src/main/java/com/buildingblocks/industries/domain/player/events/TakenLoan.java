package com.buildingblocks.industries.domain.player.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class TakenLoan extends DomainEvent {
    private String id;
    private Integer amount;
    private Integer reductionbudget;
    private Integer updatedBudget;

    public TakenLoan() {
        super(EventsEnum.TAKEN_LOAN.name());
    }

    public TakenLoan(String id, Integer amount, Integer reductionbudget, Integer updatedBudget) {
        super(EventsEnum.TAKEN_LOAN.name());
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
