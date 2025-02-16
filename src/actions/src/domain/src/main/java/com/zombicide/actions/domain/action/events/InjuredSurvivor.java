package com.zombicide.actions.domain.action.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class InjuredSurvivor extends DomainEvent {
	private final String id;
	private final String nameSurvivor;
	private final String position;
	private final Integer damage;
	private final String currentState;

	public InjuredSurvivor(String id, String nameSurvivor, String position, Integer damage, String currentState) {
		super(EventsEnum.INJURED_SURVIVOR.name());
		this.id = id;
		this.nameSurvivor = nameSurvivor;
		this.position = position;
		this.damage = damage;
		this.currentState = currentState;
	}

	public String getId() {
		return id;
	}

	public String getNameSurvivor() {
		return nameSurvivor;
	}

	public String getPosition() {
		return position;
	}

	public Integer getDamage() {
		return damage;
	}

	public String getCurrentState() {
		return currentState;
	}
}