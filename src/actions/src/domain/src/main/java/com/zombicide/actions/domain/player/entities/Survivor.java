package com.zombicide.actions.domain.player.entities;

import com.zombicide.actions.domain.player.values.Experience;
import com.zombicide.actions.domain.player.values.SurvivorId;
import com.zombicide.actions.domain.shared.values.CurrentState;
import com.zombicide.actions.domain.shared.values.Name;
import com.zombicide.actions.domain.shared.values.Position;
import com.zombicide.shared.domain.generic.Entity;

public class Survivor extends Entity<SurvivorId> {
	private Name name;
	private Experience experience;
	private Position position;
	private CurrentState currentState;

	//region Constructors
	public Survivor(SurvivorId identity, Name name, Experience experience, Position position, CurrentState currentState) {
		super(identity);
		this.name = name;
		this.experience = experience;
		this.position = position;
		this.currentState = currentState;
	}

	public Survivor(Name name, Experience experience, Position position, CurrentState currentState) {
		super(new SurvivorId());
		this.name = name;
		this.experience = experience;
		this.position = position;
		this.currentState = currentState;
	}
	//endregion

	//region Getters and Setters
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public CurrentState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(CurrentState currentState) {
		this.currentState = currentState;
	}
	//endregion

	//region Domain Actions
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
	//endregion
}
