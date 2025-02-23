package com.zombicide.actions.domain.player;

import com.zombicide.actions.domain.player.entities.Skill;
import com.zombicide.actions.domain.player.entities.Survivor;
import com.zombicide.actions.domain.player.entities.Weapon;
import com.zombicide.actions.domain.player.events.AddedSurvivor;
import com.zombicide.actions.domain.player.events.ChangedSurvivorPosition;
import com.zombicide.actions.domain.player.events.DiscardedWeapon;
import com.zombicide.actions.domain.player.events.ObtainedWeapon;
import com.zombicide.actions.domain.player.events.ChosenSkill;
import com.zombicide.actions.domain.player.values.PlayerId;
import com.zombicide.actions.domain.shared.values.Name;
import com.zombicide.shared.domain.generic.AggregateRoot;
import com.zombicide.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class Player extends AggregateRoot<PlayerId> {
	private List<Survivor> survivors;
	private Name name;
	private List<Survivor> availableSurvivors;
	private List<Skill> availableSkills;
	private List<Weapon> availableWeapons;

	//region Constructors
	public Player() {
		super(new PlayerId());
		survivors = new ArrayList<>();
		subscribe(new PlayerHandler(this));
	}

	private Player(PlayerId identity) {
		super(identity);
		survivors = new ArrayList<>();
		subscribe(new PlayerHandler(this));
	}
	//endregion

	//region Getters and Setters
	public List<Survivor> getSurvivors() {
		return survivors;
	}

	public void setSurvivors(List<Survivor> survivors) {
		this.survivors = survivors;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public List<Survivor> getAvailableSurvivors() {
		return availableSurvivors;
	}

	public void setAvailableSurvivors(List<Survivor> availableSurvivors) {
		this.availableSurvivors = availableSurvivors;
	}

	public List<Skill> getAvailableSkills() {
		return availableSkills;
	}

	public void setAvailableSkills(List<Skill> availableSkills) {
		this.availableSkills = availableSkills;
	}

	public List<Weapon> getAvailableWeapons() {
		return availableWeapons;
	}

	public void setAvailableWeapons(List<Weapon> availableWeapons) {
		this.availableWeapons = availableWeapons;
	}

	//endregion

	//region Domain Actions
	public void addSurvivor(String id, String playerName) {
		apply(new AddedSurvivor(id, playerName));
	}

	public void changeSurvivorPosition(String id, Integer positionX, Integer positionY) {
		apply(new ChangedSurvivorPosition(id, positionX, positionY));
	}

	public void chooseSkill(String survivorId, String skillId) {
		apply(new ChosenSkill(survivorId, skillId));
	}

	public void obtainWeapon(String id) {
		apply(new ObtainedWeapon(id));
	}

	public void discardWeapon(String weaponId, String survivorId) {
		apply(new DiscardedWeapon(weaponId, survivorId));
	}
	//endregion

	public static Player from(final String identity, final List<DomainEvent> events) {
		Player player = new Player(PlayerId.of(identity));

		events.forEach(player::apply);
		return player;
	}
}
