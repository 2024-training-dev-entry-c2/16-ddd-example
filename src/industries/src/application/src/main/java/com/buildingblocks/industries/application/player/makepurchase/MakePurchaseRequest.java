package com.buildingblocks.industries.application.player.makepurchase;

import com.buildingblocks.shared.application.Request;

public class MakePurchaseRequest extends Request {
    private final String budgetId;
    private final String transactionId;
    private final Integer amount;
    private final Integer newBudget;
    private final String reason;
    private final String resourceType;
    private final Integer updatedBudget;

    public MakePurchaseRequest(String aggregateId, String budgetId, String transactionId, Integer amount, Integer newBudget, String reason, String resourceType, Integer updatedBudget) {
        super(aggregateId);
        this.budgetId = budgetId;
        this.transactionId = transactionId;
        this.amount = amount;
        this.newBudget = newBudget;
        this.reason = reason;
        this.resourceType = resourceType;
        this.updatedBudget = updatedBudget;
    }

    public String getBudgetId() {
        return budgetId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getNewBudget() {
        return newBudget;
    }

    public String getReason() {
        return reason;
    }

    public String getResourceType() {
        return resourceType;
    }

    public Integer getUpdatedBudget() {
        return updatedBudget;
    }
}
