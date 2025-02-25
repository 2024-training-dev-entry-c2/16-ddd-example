package com.buildingblocks.industries.application.player.makepurchase;

import com.buildingblocks.shared.application.Request;

public class MakePurchaseRequest extends Request {
    private String budgetId;
    private String transactionId;
    private Integer amount;
    private Integer newBudget;
    private String reason;
    private String resourceType;
    private Integer updatedBudget;

    public MakePurchaseRequest() {
        super(null);
    }

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

    public void setBudgetId(String budgetId) {
        this.budgetId = budgetId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setNewBudget(Integer newBudget) {
        this.newBudget = newBudget;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setUpdatedBudget(Integer updatedBudget) {
        this.updatedBudget = updatedBudget;
    }
}
