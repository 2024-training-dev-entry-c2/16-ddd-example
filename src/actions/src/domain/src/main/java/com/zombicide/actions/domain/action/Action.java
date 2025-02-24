package com.zombicide.actions.domain.action;

import com.zombicide.actions.domain.action.entities.Affected;
import com.zombicide.actions.domain.action.entities.Type;
import com.zombicide.actions.domain.action.events.EliminatedZombie;
import com.zombicide.actions.domain.action.events.FoundObject;
import com.zombicide.actions.domain.action.events.InjuredSurvivor;
import com.zombicide.actions.domain.action.events.MadeAttack;
import com.zombicide.actions.domain.action.events.MadeMovement;
import com.zombicide.actions.domain.action.events.OpenedDoor;
import com.zombicide.actions.domain.action.values.ActionId;
import com.zombicide.shared.domain.generic.AggregateRoot;
import com.zombicide.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class Action extends AggregateRoot<ActionId> {
	private Type type;
	private List<Affected> affects;

	//region Constructors
	public Action() {
		super(new ActionId());
		affects = new ArrayList<>();
		subscribe(new ActionHandler(this));
	}

	private Action(ActionId identity) {
		super(identity);
		affects = new ArrayList<>();
		subscribe(new ActionHandler(this));
	}
	//endregion

	//region Getters and Setters
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Affected> getAffects() {
		return affects;
	}
	//endregion

	//region Domain Actions
	public void openDoor(Integer positionX, Integer positionY, Boolean isNoisy) {
		apply(new OpenedDoor(positionX, positionY, isNoisy));
	}

	public void attack(Integer positionX, Integer positionY, Boolean isNoisy) {
		apply(new MadeAttack(positionX, positionY, isNoisy));
	}

	public void findObject(Integer positionX, Integer positionY, Boolean isNoisy) {
		apply(new FoundObject(positionX, positionY, isNoisy));
	}

	public void move(Integer positionX, Integer positionY, Boolean isNoisy) {
		apply(new MadeMovement(positionX, positionY, isNoisy));
	}

	public void injureSurvivor(String survivorId, String nameSurvivor, Integer positionX, Integer positionY, Integer damage, String currentState) {
		apply(new InjuredSurvivor(survivorId, nameSurvivor, positionX, positionY, damage, currentState));
	}

	public void eliminateZombie(String zombieId, String nameZombie, Integer positionX, Integer positionY, Integer damage) {
		apply(new EliminatedZombie(zombieId, nameZombie, positionX, positionY, damage));
	}
	//endregion

	public static Action from(final String identity, final List<DomainEvent> events) {
		Action action = new Action(ActionId.of(identity));

		events.forEach(action::apply);
		return action;
	}
}
