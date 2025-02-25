package com.buildingblocks.industries.domain.player.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class ExecutedTransaction extends DomainEvent {
    private String Id;
    private String resourceType;
    private Integer amount;
    private Integer updatedBudget;

    public ExecutedTransaction() {
        super(EventsEnum.EXECUTED_TRANSACTION.name());
    }

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

    public void setId(String id) {
        Id = id;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setUpdatedBudget(Integer updatedBudget) {
        this.updatedBudget = updatedBudget;
    }
}
