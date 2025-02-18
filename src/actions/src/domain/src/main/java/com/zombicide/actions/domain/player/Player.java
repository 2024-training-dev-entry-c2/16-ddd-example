package com.zombicide.actions.domain.player;

import com.zombicide.actions.domain.player.entities.Survivor;
import com.zombicide.actions.domain.player.events.AddedSurvivor;
import com.zombicide.actions.domain.player.events.ChangedSurvivorPosition;
import com.zombicide.actions.domain.player.events.ChosenSkill;
import com.zombicide.actions.domain.player.events.DiscardedWeapon;
import com.zombicide.actions.domain.player.events.ObtainedWeapon;
import com.zombicide.actions.domain.player.events.UnlockedSkill;
import com.zombicide.actions.domain.player.values.PlayerId;
import com.zombicide.shared.domain.generic.AggregateRoot;
import com.zombicide.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class Player extends AggregateRoot<PlayerId> {
	private List<Survivor> survivors;
	private String name;

	//region Constructors
	public Player() {
		super(new PlayerId());
		survivors = new ArrayList<>();
		subscribe(new PlayerHandler(this));
	}

	private Player(PlayerId identity) {
		super(identity);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//endregion

	//region Domain Actions
	public void addSurvivor(String id) {
		apply(new AddedSurvivor(id));
	}

	public void changeSurvivorPosition(String id, Integer positionX, Integer positionY) {
		apply(new ChangedSurvivorPosition(id, positionX, positionY));
	}

	public void unlockSkill(String skillId, String survivorId) {
		apply(new UnlockedSkill(skillId, survivorId));
	}

	public void chooseSkill(String skillId, String survivorId) {
		apply(new ChosenSkill(skillId, survivorId));
	}

	public void obtainWeapon(String id) {
		apply(new ObtainedWeapon(id));
	}

	public void discardWeapon(String skillId, String survivorId) {
		apply(new DiscardedWeapon(skillId, survivorId));
	}
	//endregion

	public static Player from(final String identity, final List<DomainEvent> events) {
		Player player = new Player(PlayerId.of(identity));

		events.forEach(player::apply);
		return player;
	}
}
