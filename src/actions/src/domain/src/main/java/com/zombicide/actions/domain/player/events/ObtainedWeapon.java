package com.zombicide.actions.domain.player.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class ObtainedWeapon extends DomainEvent {
	private final String id;

	public ObtainedWeapon(String id) {
		super(EventsEnum.OBTAINED_WEAPON.name());
		this.id = id;
	}

	public String getId() {
		return id;
	}
}