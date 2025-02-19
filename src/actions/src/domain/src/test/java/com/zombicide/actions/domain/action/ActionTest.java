package com.zombicide.actions.domain.action;

import com.zombicide.actions.domain.action.events.EliminatedZombie;
import com.zombicide.actions.domain.action.events.FoundObject;
import com.zombicide.actions.domain.action.events.InjuredSurvivor;
import com.zombicide.actions.domain.action.events.MadeAttack;
import com.zombicide.actions.domain.action.events.MadeMovement;
import com.zombicide.actions.domain.action.events.OpenedDoor;
import com.zombicide.actions.domain.action.values.ActionId;
import com.zombicide.shared.domain.generic.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
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
		assertNotNull(action.getAffecteds());
	}

	@Test
	void rebuildAggregateSuccess() {
		Action action = Action.from("1", List.of(new OpenedDoor("Abrir puerta", "Accion para abrir una puerta", "puerta abierta", 0, 2, true)));
		assertEquals("1", action.getIdentity().getValue());
	}

	@Test
	void openDoorSuccess() {
		action.openDoor("Abrir puerta", "Accion para abrir una puerta", "puerta abierta", 0, 2, true);
		assertEquals("Abrir puerta", action.getType().getName().getValue());
		assertEquals("Accion para abrir una puerta", action.getType().getDescription().getValue());
		assertEquals("puerta abierta", action.getType().getEffect().getValue());
		assertEquals(0, action.getType().getPosition().getX());
		assertEquals(2, action.getType().getPosition().getY());
		assertEquals(true, action.getType().getNoisy().getValue());
		assertEquals(1, action.getUncommittedEvents().size());
		assertInstanceOf(OpenedDoor.class, action.getUncommittedEvents().get(0));
	}

	@Test
	void makeAttackSuccess() {
		action.attack("Hacer ataque", "Accion para hacer un ataque", "ataque", "Wanda", 1, 2, 1, "Herido", true);
		assertEquals("Hacer ataque", action.getType().getName().getValue());
		assertEquals("Accion para hacer un ataque", action.getType().getDescription().getValue());
		assertEquals("ataque", action.getType().getEffect().getValue());
		assertEquals("Wanda", action.getAffecteds().get(0).getName().getValue());
		assertEquals(1, action.getAffecteds().get(0).getPosition().getX());
		assertEquals(2, action.getAffecteds().get(0).getPosition().getY());
		assertEquals(1, action.getAffecteds().size());
		assertEquals(1, action.getUncommittedEvents().size());
		assertInstanceOf(MadeAttack.class, action.getUncommittedEvents().get(0));
	}

	@Test
	void findObjectSuccess() {
		action.findObject("Buscar objeto", "Accion para buscar un objeto", "buscar objeto", 2, 4, false);
		assertEquals("Buscar objeto", action.getType().getName().getValue());
		assertEquals("Accion para buscar un objeto", action.getType().getDescription().getValue());
		assertEquals("buscar objeto", action.getType().getEffect().getValue());
		assertEquals(2, action.getType().getPosition().getX());
		assertEquals(4, action.getType().getPosition().getY());
		assertEquals(false, action.getType().getNoisy().getValue());
		assertEquals(1, action.getUncommittedEvents().size());
		assertInstanceOf(FoundObject.class, action.getUncommittedEvents().get(0));
	}

	@Test
	void makeMovementSuccess() {
		action.move("Moverse", "Accion para moverse", "moverse", 2, 2, true);
		assertEquals("Moverse", action.getType().getName().getValue());
		assertEquals("Accion para moverse", action.getType().getDescription().getValue());
		assertEquals("moverse", action.getType().getEffect().getValue());
		assertEquals(2, action.getType().getPosition().getX());
		assertEquals(2, action.getType().getPosition().getY());
		assertEquals(true, action.getType().getNoisy().getValue());
		assertEquals(1, action.getUncommittedEvents().size());
		assertInstanceOf(MadeMovement.class, action.getUncommittedEvents().get(0));
	}

	@Test
	void injureSurvivorSuccess() {
		action.injureSurvivor("Wanda", 1, 2, 1, "Herido");
		assertEquals("Superviviente", action.getAffecteds().get(0).getTypeAffected().getValue());
		assertEquals("Wanda", action.getAffecteds().get(0).getName().getValue());
		assertEquals(1, action.getAffecteds().get(0).getPosition().getX());
		assertEquals(2, action.getAffecteds().get(0).getPosition().getY());
		assertEquals(1, action.getAffecteds().size());
		assertEquals("Herido", action.getAffecteds().get(0).getCurrentState().getValue());
		assertEquals(1, action.getAffecteds().size());
		assertEquals(1, action.getUncommittedEvents().size());
		assertInstanceOf(InjuredSurvivor.class, action.getUncommittedEvents().get(0));
	}

	@Test
	void eliminateZombieSuccess() {
		action.eliminateZombie("Zombie corredor", 1, 2, 1, "Muerto");
		assertEquals("Zombie", action.getAffecteds().get(0).getTypeAffected().getValue());
		assertEquals("Zombie corredor", action.getAffecteds().get(0).getName().getValue());
		assertEquals(1, action.getAffecteds().get(0).getPosition().getX());
		assertEquals(2, action.getAffecteds().get(0).getPosition().getY());
		assertEquals(1, action.getAffecteds().size());
		assertEquals("Muerto", action.getAffecteds().get(0).getCurrentState().getValue());
		assertEquals(1, action.getAffecteds().size());
		assertEquals(1, action.getUncommittedEvents().size());
		assertInstanceOf(EliminatedZombie.class, action.getUncommittedEvents().get(0));
	}
}