package com.zombicide.actions.domain.action;

import com.zombicide.actions.domain.action.events.EliminatedZombie;
import com.zombicide.actions.domain.action.events.FoundObject;
import com.zombicide.actions.domain.action.events.InjuredSurvivor;
import com.zombicide.actions.domain.action.events.MadeAttack;
import com.zombicide.actions.domain.action.events.MadeMovement;
import com.zombicide.actions.domain.action.events.OpenedDoor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {
	private Action action;

	@BeforeEach
	void setUp() {
		action = new Action();
	}

	@Test
	void createActionSuccess() {
		assertNotNull(action.getAffects());
	}

	@Test
	void rebuildAggregateSuccess() {
		action = Action.from("1", List.of(new OpenedDoor( 0, 2, true)));
		assertEquals("1", action.getIdentity().getValue());
	}

	@Test
	void openDoorSuccess() {
		action.openDoor(0, 2, true);
		assertEquals("Abrir puerta", action.getType().getName().getValue());
		assertEquals("Accion para abrir una puerta", action.getType().getDescription().getValue());
		assertEquals("Puerta abierta", action.getType().getEffect().getValue());
		assertEquals(0, action.getType().getPosition().getX());
		assertEquals(2, action.getType().getPosition().getY());
		assertEquals(true, action.getType().getNoisy().getValue());
		assertEquals(1, action.getUncommittedEvents().size());
		assertInstanceOf(OpenedDoor.class, action.getUncommittedEvents().get(0));
	}

	@Test
	void makeAttackSuccess() {
		action.attack( 1, 2, true);
		assertEquals("Realizar ataque", action.getType().getName().getValue());
		assertEquals("Accion para hacer un ataque", action.getType().getDescription().getValue());
		assertEquals("Ataque realizado", action.getType().getEffect().getValue());
		assertEquals(1, action.getType().getPosition().getX());
		assertEquals(2, action.getType().getPosition().getY());
		assertEquals(1, action.getUncommittedEvents().size());
		assertInstanceOf(MadeAttack.class, action.getUncommittedEvents().get(0));
	}

	@Test
	void findObjectSuccess() {
		action.findObject(2, 4, false);
		assertEquals("Buscar objeto", action.getType().getName().getValue());
		assertEquals("Accion para buscar un objeto", action.getType().getDescription().getValue());
		assertEquals("Objeto encontrado", action.getType().getEffect().getValue());
		assertEquals(2, action.getType().getPosition().getX());
		assertEquals(4, action.getType().getPosition().getY());
		assertEquals(false, action.getType().getNoisy().getValue());
		assertEquals(1, action.getUncommittedEvents().size());
		assertInstanceOf(FoundObject.class, action.getUncommittedEvents().get(0));
	}

	@Test
	void makeMovementSuccess() {
		action.move(2, 2, true);
		assertEquals("Moverse", action.getType().getName().getValue());
		assertEquals("Accion para moverse", action.getType().getDescription().getValue());
		assertEquals("Movimiento realizado", action.getType().getEffect().getValue());
		assertEquals(2, action.getType().getPosition().getX());
		assertEquals(2, action.getType().getPosition().getY());
		assertEquals(true, action.getType().getNoisy().getValue());
		assertEquals(1, action.getUncommittedEvents().size());
		assertInstanceOf(MadeMovement.class, action.getUncommittedEvents().get(0));
	}

	@Test
	void injureSurvivorSuccess() {
		action.injureSurvivor("1", "Wanda", 1, 2, 1, "Herido");
		assertEquals("Superviviente", action.getAffects().get(0).getTypeAffected().getValue());
		assertEquals("Wanda", action.getAffects().get(0).getName().getValue());
		assertEquals(1, action.getAffects().get(0).getPosition().getX());
		assertEquals(2, action.getAffects().get(0).getPosition().getY());
		assertEquals(1, action.getAffects().size());
		assertEquals("Herido", action.getAffects().get(0).getCurrentState().getValue());
		assertEquals(1, action.getAffects().size());
		assertEquals(1, action.getUncommittedEvents().size());
		assertInstanceOf(InjuredSurvivor.class, action.getUncommittedEvents().get(0));
	}

	@Test
	void eliminateZombieSuccess() {
		action.eliminateZombie("1", "Zombie corredor", 1, 2, 1);
		assertEquals("Zombie", action.getAffects().get(0).getTypeAffected().getValue());
		assertEquals("Zombie corredor", action.getAffects().get(0).getName().getValue());
		assertEquals(1, action.getAffects().get(0).getPosition().getX());
		assertEquals(2, action.getAffects().get(0).getPosition().getY());
		assertEquals(1, action.getAffects().size());
		assertEquals("Muerto", action.getAffects().get(0).getCurrentState().getValue());
		assertEquals(1, action.getAffects().size());
		assertEquals(1, action.getUncommittedEvents().size());
		assertInstanceOf(EliminatedZombie.class, action.getUncommittedEvents().get(0));
	}
}