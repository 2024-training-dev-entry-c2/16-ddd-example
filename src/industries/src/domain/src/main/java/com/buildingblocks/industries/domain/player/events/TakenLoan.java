package com.buildingblocks.industries.domain.player.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class TakenLoan extends DomainEvent {
    private final String id;
    private final Integer amount;
    private final Integer reductionbudget;
    private final Integer updatedBudget;

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
}
