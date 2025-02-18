package com.zombicide.actions.domain.action.values;

import com.zombicide.shared.domain.generic.Identity;

public class AffectedId extends Identity {
	public AffectedId() {
		super();
	}

	private AffectedId(String value) {
		super(value);
	}

	public static AffectedId of(String value) {
		return new AffectedId(value);
	}
}