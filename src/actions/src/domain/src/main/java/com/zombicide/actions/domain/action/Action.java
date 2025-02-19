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
	private List<Affected> affecteds;

	//region Constructors
	public Action() {
		super(new ActionId());
		affecteds = new ArrayList<>();
		subscribe(new ActionHandler(this));
	}

	private Action(ActionId identity) {
		super(identity);
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

	public List<Affected> getAffecteds() {
		return affecteds;
	}

	public void setAffecteds(List<Affected> affecteds) {
		this.affecteds = affecteds;
	}
	//endregion

	//region Domain Actions
	public void openDoor(String nameAction, String description, String effect, Integer positionX, Integer positionY, Boolean isNoisy) {
		apply(new OpenedDoor(nameAction, description, effect, positionX, positionY, isNoisy));
	}

	public void attack(String nameAction, String description, String effect, String nameAffected, Integer positionX, Integer positionY, Integer damage, String currentState, Boolean isNoisy) {
		apply(new MadeAttack(nameAction, description, effect, nameAffected, positionX, positionY, damage, currentState, isNoisy));
	}

	public void findObject(String nameAction, String description, String effect, Integer positionX, Integer positionY, Boolean isNoisy) {
		apply(new FoundObject(nameAction, description, effect, positionX, positionY, isNoisy));
	}

	public void move(String nameAction, String description, String effect, Integer positionX, Integer positionY, Boolean isNoisy) {
		apply(new MadeMovement(nameAction, description, effect, positionX, positionY, isNoisy));
	}

	public void injureSurvivor(String nameSurvivor, Integer positionX, Integer positionY, Integer damage, String currentState) {
		apply(new InjuredSurvivor(nameSurvivor, positionX, positionY, damage, currentState));
	}

	public void eliminateZombie(String nameZombie, Integer positionX, Integer positionY, Integer damage, String currentState) {
		apply(new EliminatedZombie(nameZombie, positionX, positionY, damage, currentState));
	}
	//endregion

	public static Action from(final String identity, final List<DomainEvent> events) {
		Action action = new Action(ActionId.of(identity));

		events.forEach(action::apply);
		return action;
	}
}
