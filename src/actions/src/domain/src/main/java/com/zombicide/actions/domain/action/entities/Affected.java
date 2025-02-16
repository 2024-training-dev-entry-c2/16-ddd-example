package com.zombicide.actions.domain.action.entities;

import com.zombicide.actions.domain.action.values.AffectedId;
import com.zombicide.actions.domain.action.values.TypeAffected;
import com.zombicide.actions.domain.shared.values.CurrentState;
import com.zombicide.actions.domain.shared.values.Damage;
import com.zombicide.actions.domain.shared.values.Name;
import com.zombicide.actions.domain.shared.values.Position;
import com.zombicide.shared.domain.generic.Entity;

public class Affected extends Entity<AffectedId> {
	private TypeAffected typeAffected;
	private Name name;
	private Position position;
	private Damage damage;
	private CurrentState currentState;

	//region Constructors
	public Affected(AffectedId identity, TypeAffected typeAffected, Name name, Position position, Damage damage, CurrentState currentState) {
		super(identity);
		this.typeAffected = typeAffected;
		this.name = name;
		this.position = position;
		this.damage = damage;
		this.currentState = currentState;
	}

	public Affected(TypeAffected typeAffected, Name name, Position position, Damage damage, CurrentState currentState) {
		super(new AffectedId());
		this.typeAffected = typeAffected;
		this.name = name;
		this.position = position;
		this.damage = damage;
		this.currentState = currentState;
	}
	//endregion

	//region Getters and Setters
	public TypeAffected getTypeAffected() {
		return typeAffected;
	}

	public void setTypeAffected(TypeAffected typeAffected) {
		this.typeAffected = typeAffected;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Damage getDamage() {
		return damage;
	}

	public void setDamage(Damage damage) {
		this.damage = damage;
	}

	public CurrentState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(CurrentState currentState) {
		this.currentState = currentState;
	}
	//endregion

	//region Public Methods
	public void changeState(CurrentState newState) {
		if (this.currentState != newState) {
			this.currentState = newState;
		}
	}

	public void changePosition(Position newPosition) {
		if (this.position != newPosition) {
			this.position = newPosition;
		}
	}

	public void receiveDamage(Damage damage) {
		Integer currentDamage = this.damage.getValue();
		this.damage = Damage.of(currentDamage - damage.getValue());
	}
	//endregion
}
