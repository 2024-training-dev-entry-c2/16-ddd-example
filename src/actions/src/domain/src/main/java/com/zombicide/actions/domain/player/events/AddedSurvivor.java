package com.zombicide.actions.domain.player.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class AddedSurvivor extends DomainEvent {
	private final String id;

	public AddedSurvivor(String id) {
		super(EventsEnum.CHOSEN_SURVIVOR.name());
		this.id = id;
	}

	public String getId() {
		return id;
	}
}