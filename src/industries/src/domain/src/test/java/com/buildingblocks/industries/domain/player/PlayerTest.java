package com.buildingblocks.industries.domain.player;

import com.buildingblocks.industries.domain.player.entities.Transaction;
import com.buildingblocks.industries.domain.player.events.*;
import com.buildingblocks.industries.domain.player.values.*;
import com.buildingblocks.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player();
        player.setBudget(Budget.of(50));
        player.setIncome(Income.of(20));
    }

    @Test
    void shouldAdjustIncome() {
        String playerId = PlayerId.of("player-123").getValue();

        player.adjustIncome(playerId, 10, "Sell material", 30);
        assertEquals(30, player.getIncome().getValue());

        List<DomainEvent> events = player.getUncommttedEvents();
        assertEquals(1, events.size());
        AdjustedIncome event = (AdjustedIncome) events.get(0);
        assertEquals(10, event.getChangedIncome());
        assertEquals("Sell material", event.getReason());
        assertEquals(30, event.getUpdatedIncome());
    }

    @Test
    void shouldEarnMoney() {
        String playerId = PlayerId.of("player-123").getValue();

        player.earnMoney(playerId, 20, 70);
        assertEquals(70, player.getBudget().getValue());

        List<DomainEvent> events = player.getUncommttedEvents();
        assertEquals(1, events.size());
        EarnedMoney event = (EarnedMoney) events.get(0);
        assertEquals(20, event.getAmount());
        assertEquals(70, event.getNewBudget());
    }

    @Test
    void shouldExecuteTransaction() {
        String playerId = PlayerId.of("player-123").getValue();

        player.ExecuteTransaction(playerId, "Coal", 15, 35);
        assertEquals(35, player.getBudget().getValue());

        List<DomainEvent> events = player.getUncommttedEvents();
        assertEquals(1, events.size());
        ExecutedTransaction event = (ExecutedTransaction) events.get(0);
        assertEquals("Coal", event.getResourceType());
        assertEquals(15, event.getAmount());
        assertEquals(35, event.getUpdatedBudget());
    }

    @Test
    void shouldSpendBudget() {
        String playerId = PlayerId.of("player-123").getValue();

        player.spendBudget(playerId, 10, 40, "Market purchase");
        assertEquals(40, player.getBudget().getValue());

        List<DomainEvent> events = player.getUncommttedEvents();
        assertEquals(1, events.size());
        SpentBudget event = (SpentBudget) events.get(0);
        assertEquals(10, event.getAmount());
        assertEquals(40, event.getNewBudget());
        assertEquals("Market purchase", event.getReason());
    }

    @Test
    void shouldTakeLoan() {
        String playerId = PlayerId.of("player-123").getValue();

        player.takeLoan(playerId, 20, 5, 65);

        assertEquals(65, player.getBudget().getValue());
        assertNotNull(player.getLoan());
        assertEquals(20, player.getLoan().getAmount().getValue());
        assertEquals(2, player.getLoan().getQuantity().getValue());

        List<DomainEvent> events = player.getUncommttedEvents();
        assertEquals(1, events.size());
        TakenLoan event = (TakenLoan) events.get(0);
        assertEquals(20, event.getAmount());
        assertEquals(5, event.getReductionbudget());
        assertEquals(65, event.getUpdatedBudget());
    }


    @Test
    void shouldReconstructPlayerFromEvents() {
        String playerId = PlayerId.of("player-123").getValue();
        DomainEvent event = new EarnedMoney(playerId, 50, 150);
        List<DomainEvent> events = List.of(event);

        Player player = Player.from(playerId, events);

        assertEquals(playerId, playerId);
        assertNotNull(player);
    }

    @Test
    void shouldSetAndGetTransactionCorrectly() {
        Player player = new Player();
        Transaction transaction = new Transaction(
                Amount.of(20),
                Reason.of("Buying resources"),
                ResourceType.of("Coal"),
                Quantity.of(2)
        );

        player.setTransaction(transaction);
        Transaction retrievedTransaction = player.getTransaction();

        assertNotNull(retrievedTransaction, "The transaction should not be null");
        assertEquals(transaction, retrievedTransaction, "The retrieved transaction should be the same as assigned");
        assertEquals(20, retrievedTransaction.getAmount().getValue(), "The transaction amount should be 20");
        assertEquals("Buying resources", retrievedTransaction.getReason().getValue(), "The transaction reason should match");
        assertEquals("Coal", retrievedTransaction.getResourceType().getValue(), "The resource type should match");
        assertEquals(2, retrievedTransaction.getQuantity().getValue(), "The transaction quantity should be 2");
    }

    @Test
    void shouldThrowExceptionWhenExecutingTransactionWithInsufficientBudget() {
        String playerId = PlayerId.of("player-123").getValue();
        player.setBudget(Budget.of(10));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                player.ExecuteTransaction(playerId, "Iron", 20, -10));

        assertEquals("Not enough budget to execute the transaction.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSpendingBudgetWithInsufficientFunds() {
        String playerId = PlayerId.of("player-123").getValue();
        player.setBudget(Budget.of(5));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                player.spendBudget(playerId, 10, -5, "Expensive purchase"));

        assertEquals("Not enough budget to spend.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTakingInvalidLoan() {
        String playerId = PlayerId.of("player-123").getValue();

        IllegalArgumentException negativeLoanException = assertThrows(IllegalArgumentException.class, () ->
                player.takeLoan(playerId, -10, 5, 40));
        assertEquals("Amount cannot be negative", negativeLoanException.getMessage());

        IllegalArgumentException zeroLoanException = assertThrows(IllegalArgumentException.class, () ->
                player.takeLoan(playerId, 0, 5, 40));
        assertEquals("The loan must be in increments of 10, greater than 0 and cannot exceed 30", zeroLoanException.getMessage());

        IllegalArgumentException notMultipleOf10Exception = assertThrows(IllegalArgumentException.class, () ->
                player.takeLoan(playerId, 25, 5, 40));
        assertEquals("The loan must be in increments of 10, greater than 0 and cannot exceed 30", notMultipleOf10Exception.getMessage());

        IllegalArgumentException exceedingLoanException = assertThrows(IllegalArgumentException.class, () ->
                player.takeLoan(playerId, 40, 5, 40));
        assertEquals("The loan must be in increments of 10, greater than 0 and cannot exceed 30", exceedingLoanException.getMessage());
    }

}
