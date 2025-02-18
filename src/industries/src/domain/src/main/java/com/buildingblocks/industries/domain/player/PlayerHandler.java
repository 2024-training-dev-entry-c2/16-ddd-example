package com.buildingblocks.industries.domain.player;

import com.buildingblocks.industries.domain.player.entities.Loan;
import com.buildingblocks.industries.domain.player.events.*;
import com.buildingblocks.industries.domain.player.values.Amount;
import com.buildingblocks.industries.domain.player.values.Budget;
import com.buildingblocks.industries.domain.player.values.Income;
import com.buildingblocks.industries.domain.player.values.Quantity;
import com.buildingblocks.shared.domain.generic.DomainActionsContainer;
import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.function.Consumer;

public class PlayerHandler extends DomainActionsContainer {

    public PlayerHandler(Player player) {
        add(adjustIncome(player));
        add(earnMoney(player));
        add(executeTransaction(player));
        add(spendBudget(player));
        add(takeLoan(player));
    }

    public Consumer<? extends DomainEvent> adjustIncome(Player player) {
        return (AdjustedIncome event) -> {
            Income updatedIncome = Income.of(event.getUpdatedIncome());
            player.setIncome(updatedIncome);
        };
    }


    public Consumer<? extends DomainEvent> earnMoney(Player player) {
        return (EarnedMoney event) -> {
            Budget updatedBudget = Budget.of(player.getBudget().getValue() + event.getAmount());
            player.setBudget(updatedBudget);
        };
    }


    public Consumer<? extends DomainEvent> executeTransaction(Player player) {
        return (ExecutedTransaction event) -> {
            if (player.getBudget().getValue() < event.getAmount())
                throw new IllegalStateException("Not enough budget to execute the transaction.");

            Budget updatedBudget = Budget.of(player.getBudget().getValue() - event.getAmount());
            player.setBudget(updatedBudget);
        };
    }


    public Consumer<? extends DomainEvent> spendBudget(Player player) {
        return (SpentBudget event) -> {
            if (player.getBudget().getValue() < event.getAmount())
                throw new IllegalStateException("Not enough budget to spend.");

            Budget updatedBudget = Budget.of(player.getBudget().getValue() - event.getAmount());
            player.setBudget(updatedBudget);
        };
    }


    public Consumer<? extends DomainEvent> takeLoan(Player player) {
        return (TakenLoan event) -> {
            Budget updatedBudget = Budget.of(player.getBudget().getValue() - event.getReductionbudget() + event.getAmount());
            player.setBudget(updatedBudget);
            Amount loanAmount = Amount.of(event.getAmount());

            if (loanAmount.getValue() <= 0 || loanAmount.getValue() % 10 != 0 || loanAmount.getValue() > 30)
                throw new IllegalArgumentException("The loan must be in increments of 10, greater than 0 and cannot exceed 30");

            Quantity loanQuantity = Quantity.of(loanAmount.getValue() / 10);
            Loan loan = new Loan(loanAmount, loanQuantity);
            player.setLoan(loan);
        };
    }

}
