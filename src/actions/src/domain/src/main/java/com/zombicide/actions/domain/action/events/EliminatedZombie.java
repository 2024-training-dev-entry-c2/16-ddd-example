package com.zombicide.actions.domain.action.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class EliminatedZombie extends DomainEvent {
	private final String id;
	private final String nameZombie;
	private final String position;
	private final Integer damage;
	private final String currentState;

	public EliminatedZombie(String id, String nameZombie, String position, Integer damage, String currentState) {
		super(EventsEnum.ELIMINATED_ZOMBIE.name());
		this.id = id;
		this.nameZombie = nameZombie;
		this.position = position;
		this.damage = damage;
		this.currentState = currentState;
	}

	public String getId() {
		return id;
	}

	public String getNameZombie() {
		return nameZombie;
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