package com.buildingblocks.industries.application.shared.playerUtils;

import java.util.List;

public class PlayerResponse {
    private final String playerId;
    private final List<Loan> loan;
    private final List<Transaction> transaction;
    private final Integer budget;
    private final Integer income;

    public PlayerResponse(String playerId, List<Loan> loan, List<Transaction> transaction, Integer budget, Integer income) {
        this.playerId = playerId;
        this.loan = loan;
        this.transaction = transaction;
        this.budget = budget;
        this.income = income;
    }

    public String getPlayerId() {
        return playerId;
    }

    public List<Loan> getLoan() {
        return loan;
    }

    public List<Transaction> getTransaction() {
        return transaction;
    }

    public Integer getBudget() {
        return budget;
    }

    public Integer getIncome() {
        return income;
    }

    public static class Loan {
        private final String id;
        private final Integer amount;
        private final Integer quantity;

        public Loan(String id, Integer amount, Integer quantity) {
            this.id = id;
            this.amount = amount;
            this.quantity = quantity;
        }

        public String getId() {
            return id;
        }

        public Integer getAmount() {
            return amount;
        }

        public Integer getQuantity() {
            return quantity;
        }
    }

    public static class Transaction {
        private final String id;
        private final Integer amount;
        private final String reason;
        private final String resourceType;
        private final Integer quantity;

        public Transaction(String id, Integer amount, String reason, String resourceType, Integer quantity) {
            this.id = id;
            this.amount = amount;
            this.reason = reason;
            this.resourceType = resourceType;
            this.quantity = quantity;
        }

        public String getId() {
            return id;
        }

        public Integer getAmount() {
            return amount;
        }

        public String getReason() {
            return reason;
        }

        public String getResourceType() {
            return resourceType;
        }

        public Integer getQuantity() {
            return quantity;
        }
    }
}
