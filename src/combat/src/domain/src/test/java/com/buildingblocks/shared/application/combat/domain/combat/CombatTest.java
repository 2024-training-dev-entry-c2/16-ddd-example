package com.buildingblocks.shared.application.combat.domain.combat;

import com.buildingblocks.shared.application.combat.domain.combat.entities.CharacterCombat;
import com.buildingblocks.shared.application.combat.domain.combat.entities.EnemyCombat;
import com.buildingblocks.shared.application.combat.domain.combat.entities.GameTurn;
import com.buildingblocks.shared.application.combat.domain.combat.events.CharacterAdded;
import com.buildingblocks.shared.application.combat.domain.combat.events.CharacterRemoved;
import com.buildingblocks.shared.application.combat.domain.combat.events.CombatFinished;
import com.buildingblocks.shared.application.combat.domain.combat.events.CombatInitiated;
import com.buildingblocks.shared.application.combat.domain.combat.events.EnemyAdded;
import com.buildingblocks.shared.application.combat.domain.combat.events.EnemyRemoved;
import com.buildingblocks.shared.application.combat.domain.combat.events.TurnEnded;
import com.buildingblocks.shared.application.combat.domain.combat.events.TurnStarted;
import com.buildingblocks.shared.application.combat.domain.combat.values.CharacterCombatID;
import com.buildingblocks.shared.application.combat.domain.combat.values.CombatStatus;
import com.buildingblocks.shared.application.combat.domain.combat.values.EnemiesId;
import com.buildingblocks.shared.application.combat.domain.combat.values.Health;
import com.buildingblocks.shared.application.combat.domain.combat.values.Initiative;
import com.buildingblocks.shared.application.combat.domain.combat.values.Name;
import com.buildingblocks.shared.application.combat.domain.combat.values.OrderInitiative;
import com.buildingblocks.shared.application.combat.domain.combat.values.ScenarioId;
import com.buildingblocks.shared.application.combat.domain.combat.values.StatusTurn;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class CombatTest {
    private Combat combat;

    @BeforeEach
    void setUp(){
        combat = new Combat();
        combat.setState(CombatStatus.of("Ready"));
        combat.setScenarioId(ScenarioId.of("scenario-1"));
;
    }

    @Test
    void testAddEnemy() {
        combat.addEnemy( "Goblin", 50, 10);
        assertEquals(1, combat.getUncommittedEvents().size());
        assertTrue(combat.getUncommittedEvents().get(0) instanceof EnemyAdded);
    }
    @Test
    void testRemoveEnemy() {
        Combat combat = new Combat(); // Usar la misma instancia
        combat.addEnemy( "Goblin", 50, 10);
        combat.addEnemy( "Goblin", 50, 10);
        combat.addEnemy( "Goblin", 50, 10);
        System.out.println("Before removal: " + combat.getEnemies());
        combat.removeEnemy("enemy-1");

        assertEquals(4, combat.getUncommittedEvents().size());
        assertTrue(combat.getUncommittedEvents().get(3) instanceof EnemyRemoved);
    }

    @Test
    void testAddCharacter() {
        combat.addCharacter( "Warrior", 100, 15);
        assertEquals(1, combat.getUncommittedEvents().size());
        assertTrue(combat.getUncommittedEvents().get(0) instanceof CharacterAdded);
    }
    @Test
    void testRemoveCharacter() {
        combat.addCharacter( "Warrior", 100, 15);
        combat.addCharacter( "Warrior", 100, 15);
        System.out.println("Before removal: " + combat.getCharacterTeam());
        combat.removeCharacter( "char-1");
        assertEquals(3, combat.getUncommittedEvents().size());
        assertTrue(combat.getUncommittedEvents().get(2) instanceof CharacterRemoved);
    }


    @Test
    void testStartCombat() {
        combat.startCombat();
        assertEquals(1, combat.getUncommittedEvents().size());
        assertTrue(combat.getUncommittedEvents().get(0) instanceof CombatInitiated);
    }
    @Test
    void testStartCombatInProgress() {
        combat.setState(CombatStatus.of("InProgress"));
        combat.startCombat();
        assertEquals(1, combat.getUncommittedEvents().size());
        assertTrue(combat.getUncommittedEvents().get(0) instanceof CombatInitiated);
    }


    @Test
    void testEndCombat() {
        combat.endCombat();
        assertEquals(1, combat.getUncommittedEvents().size());
        assertTrue(combat.getUncommittedEvents().get(0) instanceof CombatFinished);
    }

    @Test

    void testNextTurn() {
        List<Object> participants = new ArrayList<>();
        participants.add(new CharacterCombat(new CharacterCombatID("character-1"), Name.of("Hero"), Health.of(100), Initiative.of(10), new ArrayList<>()));
        participants.add(new EnemyCombat(new EnemiesId("enemy-1"), Name.of("Goblin"), Health.of(50), Initiative.of(5), new ArrayList<>()));

        OrderInitiative order1 = OrderInitiative.of(participants);
        OrderInitiative order2 = OrderInitiative.of(participants);

        GameTurn turn1 = new GameTurn(order1, StatusTurn.of("Pending"));
        GameTurn turn2 = new GameTurn(order2, StatusTurn.of("Pending"));

        combat.setTurns(new ArrayList<>(List.of(turn1, turn2)));
        combat.nextTurn();

        assertEquals(1, combat.getUncommittedEvents().size());
        assertTrue(combat.getUncommittedEvents().get(0) instanceof TurnStarted);

    }
    //
    @Test
    void testNextTurn_EndsCombat() {
        List<Object> participants = new ArrayList<>();
        participants.add(new CharacterCombat(new CharacterCombatID("character-1"), Name.of("Hero"), Health.of(100), Initiative.of(10), new ArrayList<>()));
        participants.add(new EnemyCombat(new EnemiesId("enemy-1"), Name.of("Goblin"), Health.of(50), Initiative.of(5), new ArrayList<>()));

        OrderInitiative order1 = OrderInitiative.of(participants);

        GameTurn turn1 = new GameTurn(order1, StatusTurn.of("Pending"));

        combat.setTurns(new ArrayList<>(List.of(turn1)));

        combat.nextTurn();

        assertEquals(1, combat.getUncommittedEvents().size());
        assertFalse(combat.getUncommittedEvents().get(0) instanceof CombatFinished);
    }
    @Test
    void testPreviousTurn() {
        combat.previousTurn();
        assertEquals(1, combat.getUncommittedEvents().size());
        assertTrue(combat.getUncommittedEvents().get(0) instanceof TurnEnded);
    }

    @Test
    void testSetState_Null_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> combat.setState(null));
    }

    @Test
    void testSetScenarioId_Null_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> combat.setScenarioId(null));
    }
    @Test
    void testGetInitiative() {
        CharacterCombat character = new CharacterCombat(
                new CharacterCombatID("character-1"),
                Name.of("Hero"),
                Health.of(100),
                Initiative.of(10),
                new ArrayList<>()
        );

        EnemyCombat enemy = new EnemyCombat(
                new EnemiesId("enemy-1"),
                Name.of("Goblin"),
                Health.of(50),
                Initiative.of(5),
                new ArrayList<>()
        );

        Object invalidObject = new Object();

        assertEquals(10, Combat.getInitiative(character).getValue()); // CharacterCombat
        assertEquals(5, Combat.getInitiative(enemy).getValue());     // EnemyCombat
        assertEquals(0, Combat.getInitiative(invalidObject).getValue()); // Objeto no válido
    }
    @Test
    void testGetInitiativeFrom() {
        EnemyCombat enemy = new EnemyCombat(new EnemiesId("enemy-1"), Name.of("Goblin"), Health.of(50), Initiative.of(10), new ArrayList<>());

        CharacterCombat character = new CharacterCombat(new CharacterCombatID("character-1"), Name.of("Hero"), Health.of(100), Initiative.of(15), new ArrayList<>());

        Object invalidObject = new Object();

        assertEquals(10, Combat.getInitiative(enemy).getValue());

        assertEquals(15, Combat.getInitiative(character).getValue());

        assertEquals(0, Combat.getInitiative(invalidObject).getValue());
    }
    @Test
    void testFrom() {
        List<DomainEvent> events = new ArrayList<>();
        events.add(new EnemyAdded( "Goblin", 50, 10));
        events.add(new CharacterAdded( "Hero", 100, 15));

        Combat combat = Combat.from("combat-1", events);

        assertNotNull(combat);
        assertEquals("combat-1", combat.getIdentity().getValue());

        assertEquals(2, combat.getUncommittedEvents().size());
        assertTrue(combat.getUncommittedEvents().get(0) instanceof EnemyAdded);
        assertTrue(combat.getUncommittedEvents().get(1) instanceof CharacterAdded);
    }
    @Test
    void testStartTurn() {
        Combat combat = new Combat();

        combat.getEnemies().add(new EnemyCombat(new EnemiesId("enemy-1"), Name.of("Goblin"), Health.of(50), Initiative.of(10), new ArrayList<>()));
        combat.getEnemies().add(new EnemyCombat(new EnemiesId("enemy-2"), Name.of("Orc"), Health.of(70), Initiative.of(5), new ArrayList<>()));
        combat.getCharacterTeam().add(new CharacterCombat(new CharacterCombatID("character-1"), Name.of("Hero"), Health.of(100), Initiative.of(15), new ArrayList<>()));
        combat.getCharacterTeam().add(new CharacterCombat(new CharacterCombatID("character-2"), Name.of("Mage"), Health.of(80), Initiative.of(20), new ArrayList<>()));

        combat.nextTurn();

        assertEquals(1, combat.getTurns().size());
        List<Object> orderedCombatants = combat.getTurns().get(0).getOrder().getParticipants();
        assertEquals("Mage", ((CharacterCombat) orderedCombatants.get(0)).getName().getValue()); // Mage (iniciativa 20)
        assertEquals("Hero", ((CharacterCombat) orderedCombatants.get(1)).getName().getValue());  // Hero (iniciativa 15)
        assertEquals("Goblin", ((EnemyCombat) orderedCombatants.get(2)).getName().getValue());    // Goblin (iniciativa 10)
        assertEquals("Orc", ((EnemyCombat) orderedCombatants.get(3)).getName().getValue());       // Orc (iniciativa 5)
    }
}