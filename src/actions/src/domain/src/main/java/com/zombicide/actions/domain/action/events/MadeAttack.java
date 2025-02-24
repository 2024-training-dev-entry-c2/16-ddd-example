package com.zombicide.actions.domain.action.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class MadeAttack extends DomainEvent {
	private final Integer positionX;
	private final Integer positionY;
	private final Boolean isNoisy;

	public MadeAttack(Integer positionX, Integer positionY, Boolean isNoisy) {
		super(EventsEnum.MADE_ATTACK.name());
		this.positionX = positionX;
		this.positionY = positionY;
		this.isNoisy = isNoisy;
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