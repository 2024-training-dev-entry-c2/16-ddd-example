package com.buildingblocks.industries.domain.player;

import com.buildingblocks.industries.domain.player.events.*;
import com.buildingblocks.industries.domain.player.values.Budget;
import com.buildingblocks.industries.domain.player.values.Income;
import com.buildingblocks.industries.domain.player.values.PlayerId;
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
}
