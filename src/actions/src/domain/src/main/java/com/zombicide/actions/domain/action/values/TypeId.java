package com.zombicide.actions.domain.action.values;

import com.zombicide.shared.domain.generic.Identity;

public class TypeId extends Identity {
	public TypeId() {
		super();
	}

	private TypeId(String value) {
		super(value);
	}

	public static TypeId of(String value) {
		return new TypeId(value);
	}
}