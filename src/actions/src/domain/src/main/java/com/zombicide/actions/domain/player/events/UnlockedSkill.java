package com.zombicide.actions.domain.player.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class UnlockedSkill extends DomainEvent {
	private final String id;
	private final String nameSkill;
	private final String description;
	private final Integer unlockPoints;
	private final Integer experience;

	public UnlockedSkill(String id, String nameSkill, String description, Integer unlockPoints, Integer experience) {
		super(EventsEnum.UNLOCKED_SKILL.name());
		this.id = id;
		this.nameSkill = nameSkill;
		this.description = description;
		this.unlockPoints = unlockPoints;
		this.experience = experience;
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

	public Integer getExperience() {
		return experience;
	}
}