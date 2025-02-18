package com.zombicide.actions.domain.player.values;

import com.zombicide.shared.domain.generic.Identity;

public class SkillId extends Identity {
	public SkillId() {
		super();
	}

	private SkillId(String value) {
		super(value);
	}

	public static SkillId of(String value) {
		return new SkillId(value);
	}
}