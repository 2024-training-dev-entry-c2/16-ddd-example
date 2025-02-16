package com.zombicide.actions.domain.player.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class AddedSurvivor extends DomainEvent {
	private final String id;
	private final String nameSurvivor;
	private final Integer experience;
	private final String position;
	private final String currentState;

	public AddedSurvivor(String id, String nameSurvivor, Integer experience, String position, String currentState) {
		super(EventsEnum.CHOSEN_SURVIVOR.name());
		this.id = id;
		this.nameSurvivor = nameSurvivor;
		this.experience = experience;
		this.position = position;
		this.currentState = currentState;
	}

	public String getId() {
		return id;
	}

	public String getNameSurvivor() {
		return nameSurvivor;
	}

	public Integer getExperience() {
		return experience;
	}

	public String getPosition() {
		return position;
	}

	public String getCurrentState() {
		return currentState;
	}
}