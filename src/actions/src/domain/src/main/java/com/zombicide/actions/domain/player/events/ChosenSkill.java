package com.zombicide.actions.domain.player.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class ChosenSkill extends DomainEvent {
	private final String skillId;
	private final String survivorId;

	public ChosenSkill(String skillId, String survivorId) {
		super(EventsEnum.CHOSEN_SKILL.name());
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