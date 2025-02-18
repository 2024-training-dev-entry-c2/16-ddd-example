package com.zombicide.actions.domain.player.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class UnlockedSkill extends DomainEvent {
	private final String skillId;
	private final String survivorId;

	public UnlockedSkill(String skillId, String survivorId) {
		super(EventsEnum.UNLOCKED_SKILL.name());
		this.skillId = skillId;
		this.survivorId = survivorId;
	}

	public String getSkillId() {
		return skillId;
	}

	public String getSurvivorId() {
		return survivorId;
	}
}