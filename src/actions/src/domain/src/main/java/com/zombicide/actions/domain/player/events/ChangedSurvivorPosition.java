package com.zombicide.actions.domain.player.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class ChangedSurvivorPosition extends DomainEvent {
	private final String id;
	private final Integer positionX;
	private final Integer positionY;

	public ChangedSurvivorPosition(String id, Integer positionX, Integer positionY) {
		super(EventsEnum.CHANGED_SURVIVOR_POSITION.name());
		this.id = id;
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public String getId() {
		return id;
	}

	public Integer getPositionX() {
		return positionX;
	}

	public Integer getPositionY() {
		return positionY;
	}
}