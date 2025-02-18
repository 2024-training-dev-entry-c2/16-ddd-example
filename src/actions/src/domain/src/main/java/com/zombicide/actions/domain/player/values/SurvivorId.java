package com.zombicide.actions.domain.player.values;

import com.zombicide.shared.domain.generic.Identity;

public class SurvivorId extends Identity {
	public SurvivorId() {
		super();
	}

	private SurvivorId(String value) {
		super(value);
	}

	public static SurvivorId of(String value) {
		return new SurvivorId(value);
	}
}