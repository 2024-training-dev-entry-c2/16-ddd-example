package com.zombicide.actions.domain.action.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class EliminatedZombie extends DomainEvent {
	private final String zombieId;
	private final String nameZombie;
	private final Integer positionX;
	private final Integer positionY;
	private final Integer damage;

	public EliminatedZombie(String zombieId, String nameZombie, Integer positionX, Integer positionY, Integer damage) {
		super(EventsEnum.ELIMINATED_ZOMBIE.name());
		this.zombieId = zombieId;
		this.nameZombie = nameZombie;
		this.positionX = positionX;
		this.positionY = positionY;
		this.damage = damage;
	}

	public String getZombieId() {
		return zombieId;
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
}