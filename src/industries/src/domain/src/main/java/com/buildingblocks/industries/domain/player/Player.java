package com.buildingblocks.industries.domain.player;

import com.buildingblocks.industries.domain.player.entities.Loan;
import com.buildingblocks.industries.domain.player.entities.Transaction;
import com.buildingblocks.industries.domain.player.events.*;
import com.buildingblocks.industries.domain.player.values.Budget;
import com.buildingblocks.industries.domain.player.values.Income;
import com.buildingblocks.industries.domain.player.values.PlayerId;
import com.buildingblocks.shared.domain.generic.AggregateRoot;
import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.List;

public class Player extends AggregateRoot<PlayerId> {
    private Loan loan;
    private Transaction transaction;
    private Budget budget;
    private Income income;

    // region Constructors
    public Player() {
        super(new PlayerId());
        subscribe(new PlayerHandler(this));
    }

    private Player(PlayerId identity) {
        super(identity);
    }
    // endregion

    // region Getters and Setters
    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }
    // endregion

    // region Domain Actions
    public void adjustIncome(String id, Integer changedIncome, String reason, Integer updatedIncome) {
        apply(new AdjustedIncome(id, changedIncome, reason, updatedIncome));
    }

    public void earnMoney(String id, Integer amount, Integer newBudget) {
        apply(new EarnedMoney(id, amount, newBudget));
    }

    public void ExecuteTransaction(String id, String resourceType, Integer amount, Integer updatedBudget) {
        apply(new ExecutedTransaction(id, resourceType, amount, updatedBudget));
    }

    public void spendBudget(String id, Integer amount, Integer newBudget, String reason) {
        apply(new SpentBudget(id, amount, newBudget, reason));
    }

    public void takeLoan(String id, Integer amount, Integer reductionbudget, Integer updatedBudget) {
        apply(new TakenLoan(id, amount, reductionbudget, updatedBudget));
    }
    // endregion

    // region Public Methods
    public static Player from(final String identity, final List<DomainEvent> events) {
        Player player = new Player(PlayerId.of(identity));

        events.forEach(player::apply);
        return player;
    }
    // endregion
}
