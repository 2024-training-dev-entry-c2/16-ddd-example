package com.zombicide.actions.domain.action.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class OpenedDoor extends DomainEvent {
	private final String nameAction;
	private final String description;
	private final String effect;
	private final Integer positionX;
	private final Integer positionY;
	private final Boolean isNoisy;

	public OpenedDoor(String nameAction, String description, String effect, Integer positionX, Integer positionY, Boolean isNoisy) {
		super(EventsEnum.OPENED_DOOR.name());
		this.nameAction = nameAction;
		this.description = description;
		this.effect = effect;
		this.positionX = positionX;
		this.positionY = positionY;
		this.isNoisy = isNoisy;
	}

	public String getNameAction() {
		return nameAction;
	}

	public String getDescription() {
		return description;
	}

	public String getEffect() {
		return effect;
	}

	public Integer getPositionX() {
		return positionX;
	}

	public Integer getPositionY() {
		return positionY;
	}

	public Boolean getNoisy() {
		return isNoisy;
	}
}