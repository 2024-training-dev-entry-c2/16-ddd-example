package com.zombicide.actions.domain.player.entities;

import com.zombicide.actions.domain.player.values.Experience;
import com.zombicide.actions.domain.player.values.SkillId;
import com.zombicide.actions.domain.player.values.UnlockPoints;
import com.zombicide.actions.domain.shared.values.Description;
import com.zombicide.actions.domain.shared.values.Name;
import com.zombicide.shared.domain.generic.Entity;

public class Skill extends Entity<SkillId> {
	private Name name;
	private Description description;
	private UnlockPoints unlockPoints;

	//region Constructors
	public Skill(SkillId identity, Name name, Description description, UnlockPoints unlockPoints) {
		super(identity);
		this.name = name;
		this.description = description;
		this.unlockPoints = unlockPoints;
	}

	public Skill(Name name, Description description, UnlockPoints unlockPoints) {
		super(new SkillId());
		this.name = name;
		this.description = description;
		this.unlockPoints = unlockPoints;
	}
	//endregion

	//region Getters and Setters
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

	public UnlockPoints getUnlockPoints() {
		return unlockPoints;
	}

	public void setUnlockPoints(UnlockPoints unlockPoints) {
		this.unlockPoints = unlockPoints;
	}
	//endregion

	//region Domain Actions
	public boolean validateUnlockPoints(Experience experience) {
		return minExperience(experience) && maxExperience(experience);
	}
	//endregion

	//region Private Methods
	private boolean minExperience(Experience experience) {
		return experience.getValue() >= unlockPoints.getValue();
	}

	private boolean maxExperience(Experience experience) {
		return experience.getValue() <= unlockPoints.getValue() + 10;
	}
	//endregion
}
