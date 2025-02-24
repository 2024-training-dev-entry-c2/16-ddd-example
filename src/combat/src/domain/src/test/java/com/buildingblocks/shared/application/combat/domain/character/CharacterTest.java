package com.buildingblocks.shared.application.combat.domain.character;

import com.buildingblocks.shared.application.combat.domain.character.entities.ActionTaken;
import com.buildingblocks.shared.application.combat.domain.character.entities.StatusActivated;
import com.buildingblocks.shared.application.combat.domain.character.events.AppliedStatus;
import com.buildingblocks.shared.application.combat.domain.character.events.RegisteredAction;
import com.buildingblocks.shared.application.combat.domain.character.events.RemovedStatus;
import com.buildingblocks.shared.application.combat.domain.character.events.SufferedDamage;
import com.buildingblocks.shared.application.combat.domain.character.events.TerminatedTurn;
import com.buildingblocks.shared.application.combat.domain.character.events.beCured;
import com.buildingblocks.shared.application.combat.domain.character.values.CharacterId;
import com.buildingblocks.shared.application.combat.domain.character.values.Damage;
import com.buildingblocks.shared.application.combat.domain.character.values.EffectType;
import com.buildingblocks.shared.application.combat.domain.character.values.Health;
import com.buildingblocks.shared.application.combat.domain.character.values.Objetive;
import com.buildingblocks.shared.application.combat.domain.character.values.Result;
import com.buildingblocks.shared.application.combat.domain.character.values.TypeAction;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    private Character character;

    @BeforeEach
    void setUp() {
        character = new Character();
    }

    @Test
    void testApplyEffect() {
        EffectType effect = EffectType.of("Poison", 5, 3);
        character.applyEffect(effect);

        List<StatusActivated> effectActives = character.getEffectActives();
        assertNotNull(effectActives);
        assertEquals(1, effectActives.size());
        assertEquals("Poison", effectActives.get(0).getName().getValue());
        assertEquals(3, effectActives.get(0).getImpact().getValue());
        assertEquals(5, effectActives.get(0).getRemainingTurns().getValue());
        assertInstanceOf(AppliedStatus.class,character.getUncommittedEvents().get(0));

    }

    @Test
    void testRemoveEffect() {
        EffectType effect =  EffectType.of("Poison", 5, 3);
        character.applyEffect(effect);

        List<StatusActivated> effectActives = character.getEffectActives();
        assertNotNull(effectActives);
        assertEquals(1, effectActives.size());

        String effectId = effectActives.get(0).getIdentity().getValue();
        character.removeEffect(effectId);

        effectActives = character.getEffectActives();
        assertTrue(effectActives.isEmpty());
        assertInstanceOf(RemovedStatus.class,character.getUncommittedEvents().get(1));

    }

    @Test
    void testRegisterAction() {
        ActionTaken action = new ActionTaken(
                TypeAction.of("Attack"),
                Objetive.of(1),
                Damage.of(10),
                EffectType.of("Burn", 2, 3),
                Result.of("Success")
        );

        character.registerAction(action);

        List<ActionTaken> historyActions = character.getHistoryActions();
        assertNotNull(historyActions);
        assertEquals(1, historyActions.size());
        assertEquals("Burn", historyActions.get(0).getTypeEffect().getNameEffect());
        assertEquals(1, historyActions.get(0).getObjetive().getValue());
        assertEquals(10, historyActions.get(0).getDamage().getValue());
        assertEquals("Success", historyActions.get(0).getResult().getValue());
        assertInstanceOf(RegisteredAction.class,character.getUncommittedEvents().get(0));

    }

    @Test
    void testReceiveDamage() {
        character.setHealth(Health.of(100));
        character.receiveDamage(20);

        assertEquals(80, character.getHealth().getValue());
        assertInstanceOf(SufferedDamage.class,character.getUncommittedEvents().get(0));

    }

    @Test
    void testBeCured() {
        character.setHealth(Health.of(50));
        character.beCured(30);

        assertEquals(80, character.getHealth().getValue());
        assertInstanceOf(beCured.class,character.getUncommittedEvents().get(0));

    }

    @Test
    void testEndTurn() {
        EffectType effect =  EffectType.of("Poison", 5, 3);
        character.applyEffect(effect);

        character.endTurn();

        List<StatusActivated> effectActives = character.getEffectActives();
        assertEquals(4, effectActives.get(0).getRemainingTurns().getValue());
        assertInstanceOf(TerminatedTurn.class,character.getUncommittedEvents().get(1));

    }
@Test
    void testFrom() {
        String characterId = "character-123";
        List<DomainEvent> events = new ArrayList<>();

        events.add(new SufferedDamage(characterId, 20));
        events.add(new beCured(characterId, 10));

        Character character = Character.from(characterId, events);

        assertNotNull(character, "El personaje no debe ser null");
        assertEquals(CharacterId.of(characterId).getValue(), character.getIdentity().getValue(), "El ID del personaje debe coincidir");
        assertNotNull(character.getHealth(), "La salud no debe ser null");
        assertEquals(90, character.getHealth().getValue(), "La salud debe ser 90 después de aplicar los eventos");

    }
}