package com.zombicide.actions.domain.action.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class EliminatedZombie extends DomainEvent {
	private final String nameZombie;
	private final Integer positionX;
	private final Integer positionY;
	private final Integer damage;
	private final String currentState;

	public EliminatedZombie(String nameZombie, Integer positionX, Integer positionY, Integer damage, String currentState) {
		super(EventsEnum.ELIMINATED_ZOMBIE.name());
		this.nameZombie = nameZombie;
		this.positionX = positionX;
		this.positionY = positionY;
		this.damage = damage;
		this.currentState = currentState;
	}

	public String getNameZombie() {
		return nameZombie;
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