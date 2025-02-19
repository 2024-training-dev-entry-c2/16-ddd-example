package com.zombicide.actions.domain.player.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class AddedSurvivor extends DomainEvent {
	private final String id;
	private final String playerName;

	public AddedSurvivor(String id, String playerName) {
		super(EventsEnum.CHOSEN_SURVIVOR.name());
		this.id = id;
		this.playerName = playerName;
	}

	public String getId() {
		return id;
	}

	public String getPlayerName() {
		return playerName;
	}
}