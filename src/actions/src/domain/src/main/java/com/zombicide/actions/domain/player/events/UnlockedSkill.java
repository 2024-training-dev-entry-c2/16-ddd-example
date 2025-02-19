package com.zombicide.actions.domain.player.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class UnlockedSkill extends DomainEvent {
	private final String survivorId;
	private final String skillId;

	public UnlockedSkill(String survivorId, String skillId) {
		super(EventsEnum.UNLOCKED_SKILL.name());
		this.survivorId = survivorId;
		this.skillId = skillId;
	}

	public String getSurvivorId() {
		return survivorId;
	}

	public String getSkillId() {
		return skillId;
	}
}