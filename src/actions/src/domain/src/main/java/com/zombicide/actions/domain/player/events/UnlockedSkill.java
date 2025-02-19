package com.zombicide.actions.domain.player.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class UnlockedSkill extends DomainEvent {
	private final String survivorId;

	public UnlockedSkill(String survivorId) {
		super(EventsEnum.UNLOCKED_SKILL.name());
		this.survivorId = survivorId;
	}

	public String getSurvivorId() {
		return survivorId;
	}
}