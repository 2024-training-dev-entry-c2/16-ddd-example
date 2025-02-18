package com.buildingblocks.industries.domain.player.entities;

import com.buildingblocks.industries.domain.player.values.Amount;
import com.buildingblocks.industries.domain.player.values.LoanId;
import com.buildingblocks.industries.domain.player.values.Quantity;
import com.buildingblocks.shared.domain.generic.Entity;

public class Loan  extends Entity<LoanId> {
    private Amount amount;
    private Quantity quantity;

    // region Constructors
    public Loan(Amount amount, Quantity quantity) {
        super(new LoanId());
        this.amount = amount;
        this.quantity = quantity;
    }

    public Loan(LoanId identity, Amount amount, Quantity quantity) {
        super(identity);
        this.amount = amount;
        this. quantity = quantity;
    }
    // endregion

    // region Public Methods
    public void take(Amount amount) {
        if (amount.getValue() <= 0 || amount.getValue() % 10 != 0 || amount.getValue() > 30) {
            throw new IllegalArgumentException("The loan must be in increments of 10, greater than 0 and cannot exceed 30");
        }
        int loanCount = amount.getValue() / 10;

        this.amount = Amount.of(this.amount.getValue() + amount.getValue());
        this.quantity = Quantity.of(this.quantity.getValue() + loanCount);
    }
    // endregion

    // region Getters and Setters
    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    // endregion
}
