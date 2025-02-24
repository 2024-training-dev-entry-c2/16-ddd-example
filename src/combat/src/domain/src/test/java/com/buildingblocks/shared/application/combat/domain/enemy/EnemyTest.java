package com.buildingblocks.shared.application.combat.domain.enemy;

import com.buildingblocks.shared.application.combat.domain.enemy.entities.ActionTaken;
import com.buildingblocks.shared.application.combat.domain.enemy.entities.Skill;
import com.buildingblocks.shared.application.combat.domain.enemy.entities.StatusActivated;
import com.buildingblocks.shared.application.combat.domain.enemy.events.AppliedStatus;
import com.buildingblocks.shared.application.combat.domain.enemy.events.BeCured;
import com.buildingblocks.shared.application.combat.domain.enemy.events.RegisteredAction;
import com.buildingblocks.shared.application.combat.domain.enemy.events.SkillAdded;
import com.buildingblocks.shared.application.combat.domain.enemy.events.SufferedDamage;
import com.buildingblocks.shared.application.combat.domain.enemy.events.TerminatedTurn;
import com.buildingblocks.shared.application.combat.domain.enemy.events.UsedCard;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Damage;
import com.buildingblocks.shared.application.combat.domain.enemy.values.EffectType;
import com.buildingblocks.shared.application.combat.domain.enemy.values.EnemyId;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Health;
import com.buildingblocks.shared.application.combat.domain.enemy.values.HistoricalActionsId;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Impact;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Name;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Objetive;
import com.buildingblocks.shared.application.combat.domain.enemy.values.RemainingTurn;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Result;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Scope;
import com.buildingblocks.shared.application.combat.domain.enemy.values.StatusCondition;
import com.buildingblocks.shared.application.combat.domain.enemy.values.TypeAction;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnemyTest {
    private Enemy enemy;

    @BeforeEach
    void setUp() {
        enemy = new Enemy();
    }

    @Test
    void testSufferDamage() {
        enemy.setHealth(Health.of(100));
        enemy.sufferDamage(20);
        assertEquals(80, enemy.getHealth().getValue(), "La salud debería reducirse en 20");
        assertInstanceOf(SufferedDamage.class,enemy.getUncommittedEvents().get(0));

    }

    @Test
    void testBeCured() {
        enemy.setHealth(Health.of(80));
        enemy.beCured(20);
        assertEquals(100, enemy.getHealth().getValue(), "La salud debería aumentar en 20");
        assertInstanceOf(BeCured.class,enemy.getUncommittedEvents().get(0));

    }

    @Test
    void testApplyStatus() {
        StatusActivated status = new StatusActivated(
                Name.of("Poison"),
                Impact.of(5),
                RemainingTurn.of(3)
        );
        enemy.applyState(status);
        System.out.println(enemy.getStatusActivateds().get(0).getName().getValue());
        assertTrue(enemy.getStatusActivateds().get(0).getName().getValue().equals(status.getName().getValue()), "El estado debería estar aplicado");
        assertInstanceOf(AppliedStatus.class,enemy.getUncommittedEvents().get(0));

    }

    @Test
    void testRemoveStatus() {
        StatusActivated status = new StatusActivated(

                Name.of("Poison"),
                Impact.of(5),
                RemainingTurn.of(3)
        );
        enemy.applyState(status);
        enemy.removeState(status.getIdentity().getValue());
        assertFalse(enemy.getStatusActivateds().contains(status), "El estado debería ser removido");

    }
    @Test
    void testRegisterAction() {
        ActionTaken action = new ActionTaken(
                new HistoricalActionsId(),
                TypeAction.of("Attack"),
                Objetive.of(1),
                Damage.of(10),
                EffectType.of("Damage", 1, 5),
                Result.of("Success")
        );
        enemy.actionRegister(action);
        System.out.println(enemy.getActionTakens().size());
        assertTrue(enemy.getActionTakens().get(0).getAction().getValue().equals(action.getAction().getValue()), "La acción debería estar registrada");
        assertInstanceOf(RegisteredAction.class,enemy.getUncommittedEvents().get(0));

    }

    @Test
    void testEndTurn() {
        StatusActivated status = new StatusActivated(
                Name.of("Poison"),
                Impact.of(5),
                RemainingTurn.of(1)
        );
        enemy.applyState(status);
        enemy.endturn();
        assertFalse(enemy.getStatusActivateds().contains(status), "El estado debería ser removido después de terminar el turno");
        assertInstanceOf(TerminatedTurn.class,enemy.getUncommittedEvents().get(1));

    }
    @Test
    void testUseCard() {

        Skill skill = new Skill(
                Damage.of(15),
                EffectType.of("Damage", 2, 10),
                Scope.of(1),
                StatusCondition.of("None")
        );

        enemy.setSkills(List.of(skill));
        assertEquals(1, enemy.getSkills().size(), "El enemigo debería tener una habilidad agregada");
        enemy.useCard(0, "enemy-2");
        assertEquals(1, enemy.getSkills().size(), "El enemigo debería seguir teniendo la habilidad después de usarla");
        assertTrue(enemy.getSkills().contains(skill), "La habilidad debería seguir en la lista del enemigo");
        assertInstanceOf(UsedCard.class,enemy.getUncommittedEvents().get(0));


    }
    @Test
    void testAddSkill() {
        Skill skill = new Skill(
                Damage.of(15),
                EffectType.of("Damage", 2, 10),
                Scope.of(1),
                StatusCondition.of("None")
        );
        enemy.addSkill(skill);
        System.out.println(enemy.getSkills().size());
        assertFalse(enemy.getSkills().contains(skill), "La habilidad debería estar agregada");
        assertInstanceOf(SkillAdded.class,enemy.getUncommittedEvents().get(0));

    }

    @Test
    public void testFrom() {
        String enemyId = "enemy-123";
        List<DomainEvent> events = new ArrayList<>();
        events.add(new SufferedDamage(enemyId, 10));
        Enemy enemy = Enemy.from(enemyId, events);
        enemy.setHealth(Health.of(10));

        assertEquals(EnemyId.of(enemyId).getValue(), enemy.getIdentity().getValue());
        assertEquals(0, enemy.getActionTakens().size());
    }
}