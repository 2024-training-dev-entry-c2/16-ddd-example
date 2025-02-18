package com.buildingblocks.industries.domain.player.entities;

import com.buildingblocks.industries.domain.player.Player;
import com.buildingblocks.industries.domain.player.values.*;
import com.buildingblocks.shared.domain.generic.Entity;

public class Transaction extends Entity<TransactionId> {
    private Amount amount;
    private Reason reason;
    private ResourceType resourceType;
    private Quantity quantity;

    // region Constructors
    public Transaction(Amount amount, Reason reason, ResourceType resourceType, Quantity quantity) {
        super(new TransactionId());
        this.amount = amount;
        this.reason = reason;
        this.resourceType = resourceType;
        this.quantity = quantity;
    }

    public Transaction(TransactionId identity, Amount amount, Reason reason, ResourceType resourceType, Quantity quantity) {
        super(identity);
        this.amount = amount;
        this.reason = reason;
        this.resourceType = resourceType;
        this.quantity = quantity;
    }
    // endregion

    // region Public Methods
    public void execute(Player player) {
        if (amount.getValue() <= 0) throw new IllegalArgumentException("The transaction amount must be greater than 0");

        int newBudgetValue = player.getBudget().getValue() - amount.getValue();

        if (newBudgetValue < 0) throw new IllegalStateException("Not enough budget to complete transaction");
        player.setBudget(Budget.of(newBudgetValue));
    }
    // endregion

    // region Getters and Setters
    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }
    // endregion
}
