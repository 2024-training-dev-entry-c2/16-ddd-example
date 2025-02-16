package com.zombicide.actions.domain.player.entities;

import com.zombicide.actions.domain.player.values.Experience;
import com.zombicide.actions.domain.player.values.SurvivorId;
import com.zombicide.actions.domain.shared.values.CurrentState;
import com.zombicide.actions.domain.shared.values.Name;
import com.zombicide.actions.domain.shared.values.Position;
import com.zombicide.shared.domain.generic.Entity;

import java.util.List;

public class Survivor extends Entity<SurvivorId> {
	private Name name;
	private Experience experience;
	private Position position;
	private CurrentState currentState;
	private List<Skill> skills;
	private List<Weapon> weapons;

	//region Constructors
	public Survivor(SurvivorId identity, Name name, Experience experience, Position position, CurrentState currentState, List<Skill> skills, List<Weapon> weapons) {
		super(identity);
		this.name = name;
		this.experience = experience;
		this.position = position;
		this.currentState = currentState;
		this.skills = skills;
		this.weapons = weapons;
	}

	public Survivor( Name name, Experience experience, Position position, CurrentState currentState, List<Skill> skills, List<Weapon> weapons) {
		super(new SurvivorId());
		this.name = name;
		this.experience = experience;
		this.position = position;
		this.currentState = currentState;
		this.skills = skills;
		this.weapons = weapons;
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

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Weapon> getWeapons() {
		return weapons;
	}

	public void setWeapons(List<Weapon> weapons) {
		this.weapons = weapons;
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

	public void addSkill(Skill skill) {
		this.skills.add(skill);
	}

	public void addWeapon(Weapon weapon) {
		this.weapons.add(weapon);
	}

	public void removeWeapon(Weapon weapon) {
		this.weapons.remove(weapon);
	}
	//endregion
}
