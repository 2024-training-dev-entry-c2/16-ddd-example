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

import java.util.List;

public class Action extends AggregateRoot<ActionId> {
	private Type type;
	private List<Affected> affecteds;

	//region Constructors
	public Action() {
		super(new ActionId());
	}

	private Action(ActionId identity) {
		super(identity);
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
	public void openDoor(String id, String nameAction, String description, String effect, String position, Boolean isNoisy) {
		apply(new OpenedDoor(id, nameAction, description, effect, position, isNoisy));
	}

	public void attack(String id, String nameAction, String description, String effect, String typeAffectedId, String nameAffected, String positionAffected, Integer damage, String currentState, Boolean isNoisy) {
		apply(new MadeAttack(id, nameAction, description, effect, typeAffectedId, nameAffected, positionAffected, damage, currentState, isNoisy));
	}

	public void findObject(String id, String nameAction, String description, String effect, String position, Boolean isNoisy) {
		apply(new FoundObject(id, nameAction, description, effect, position, isNoisy));
	}

	public void move(String id, String nameAction, String description, String effect, String position, Boolean isNoisy) {
		apply(new MadeMovement(id, nameAction, description, effect, position, isNoisy));
	}

	public void injureSurvivor(String id, String nameSurvivor, String position, Integer damage, String currentState) {
		apply(new InjuredSurvivor(id, nameSurvivor, position, damage, currentState));
	}

	public void eliminateZombie(String id, String nameZombie, String position, Integer damage, String currentState) {
		apply(new EliminatedZombie(id, nameZombie, position, damage, currentState));
	}
	//endregion
}
