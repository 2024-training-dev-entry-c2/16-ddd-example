package com.zombicide.actions.domain.player.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class ChangedSurvivorPosition extends DomainEvent {
	private final String id;
	private final String position;

	public ChangedSurvivorPosition(String id, String position) {
		super(EventsEnum.CHANGED_SURVIVOR_POSITION.name());
		this.id = id;
		this.position = position;
	}

	public String getId() {
		return id;
	}

	public String getPosition() {
		return position;
	}
}