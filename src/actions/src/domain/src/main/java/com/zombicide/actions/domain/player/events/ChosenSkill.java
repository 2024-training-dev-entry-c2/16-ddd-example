package com.zombicide.actions.domain.player.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class ChosenSkill extends DomainEvent {
	private final String id;
	private final String nameSkill;
	private final String description;
	private final Integer unlockPoints;

	public ChosenSkill(String id, String nameSkill, String description, Integer unlockPoints) {
		super(EventsEnum.CHOSEN_SKILL.name());
		this.id = id;
		this.nameSkill = nameSkill;
		this.description = description;
		this.unlockPoints = unlockPoints;
	}

	public String getId() {
		return id;
	}

	public String getNameSkill() {
		return nameSkill;
	}

	public String getDescription() {
		return description;
	}

	public Integer getUnlockPoints() {
		return unlockPoints;
	}
}