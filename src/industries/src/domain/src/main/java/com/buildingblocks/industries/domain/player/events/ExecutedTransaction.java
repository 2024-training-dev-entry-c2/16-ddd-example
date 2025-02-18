package com.buildingblocks.industries.domain.player.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class ExecutedTransaction extends DomainEvent {
    private final String Id;
    private final String resourceType;
    private final Integer amount;
    private final Integer updatedBudget;

    public ExecutedTransaction(String id, String resourceType, Integer amount, Integer updatedBudget) {
        super(EventsEnum.EXECUTED_TRANSACTION.name());
        Id = id;
        this.resourceType = resourceType;
        this.amount = amount;
        this.updatedBudget = updatedBudget;
    }

    public String getId() {
        return Id;
    }

    public String getResourceType() {
        return resourceType;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getUpdatedBudget() {
        return updatedBudget;
    }
}
