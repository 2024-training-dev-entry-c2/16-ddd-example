package com.zombicide.actions.domain.action.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class InjuredSurvivor extends DomainEvent {
	private final String survivorId;
	private final String nameSurvivor;
	private final Integer positionX;
	private final Integer positionY;
	private final Integer damage;
	private final String currentState;

	public InjuredSurvivor(String survivorId, String nameSurvivor, Integer positionX, Integer positionY, Integer damage, String currentState) {
		super(EventsEnum.INJURED_SURVIVOR.name());
		this.survivorId = survivorId;
		this.nameSurvivor = nameSurvivor;
		this.positionX = positionX;
		this.positionY = positionY;
		this.damage = damage;
		this.currentState = currentState;
	}

	public String getSurvivorId() {
		return survivorId;
	}

	public String getNameSurvivor() {
		return nameSurvivor;
	}

	public Integer getPositionX() {
		return positionX;
	}

	public Integer getPositionY() {
		return positionY;
	}

	public Integer getDamage() {
		return damage;
	}

	public String getCurrentState() {
		return currentState;
	}
}