package com.zombicide.actions.domain.player;

import com.zombicide.actions.domain.player.entities.Survivor;
import com.zombicide.actions.domain.player.events.AddedSurvivor;
import com.zombicide.actions.domain.player.events.ChangedSurvivorPosition;
import com.zombicide.actions.domain.player.events.ChosenSkill;
import com.zombicide.actions.domain.player.events.DiscardedWeapon;
import com.zombicide.actions.domain.player.events.ObtainedWeapon;
import com.zombicide.actions.domain.player.events.SwappedWeapon;
import com.zombicide.actions.domain.player.events.UnlockedSkill;
import com.zombicide.actions.domain.player.values.PlayerId;
import com.zombicide.shared.domain.generic.AggregateRoot;

import java.util.List;

public class Player extends AggregateRoot<PlayerId> {
	private List<Survivor> survivors;
	private String name;

	//region Constructors
	public Player() {
		super(new PlayerId());
	}

	private Player(PlayerId identity) {
		super(identity);
	}
	//endregion

	//region Getters and Setters
	public List<Survivor> getSurvivors() {
		return survivors;
	}

	public void setSurvivors(List<Survivor> survivors) {
		this.survivors = survivors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//endregion

	//region Domain Actions
	public void addSurvivor(String id, String nameSurvivor, Integer experience, String position, String currentState) {
		apply(new AddedSurvivor(id, nameSurvivor, experience, position, currentState));
	}

	public void changeSurvivorPosition(String id, String newPosition) {
		apply(new ChangedSurvivorPosition(id, newPosition));
	}

	public void unlockSkill(String id, String nameSkill, String description, Integer unlockPoints, Integer experience) {
		apply(new UnlockedSkill(id, nameSkill, description, unlockPoints, experience));
	}

	public void chooseSkill(String id, String nameSkill, String description, Integer unlockPoints) {
		apply(new ChosenSkill(id, nameSkill, description, unlockPoints));
	}

	public void obtainWeapon(String id, String nameWeapon, Integer scope, Integer precision, Boolean isNoisy, Boolean openDoor) {
		apply(new ObtainedWeapon(id, nameWeapon, scope, precision, isNoisy, openDoor));
	}

	public void swapWeapon(String id, String nameWeapon, Integer scope, Integer precision, Boolean isNoisy, Boolean openDoor) {
		apply(new SwappedWeapon(id, nameWeapon, scope, precision, isNoisy, openDoor));
	}

	public void discardWeapon(String id, String nameWeapon, Integer scope, Integer precision, Boolean isNoisy, Boolean openDoor) {
		apply(new DiscardedWeapon(id, nameWeapon, scope, precision, isNoisy, openDoor));
	}
	//endregion
}
