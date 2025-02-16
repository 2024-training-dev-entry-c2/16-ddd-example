package com.zombicide.actions.domain.action.values;

import com.zombicide.shared.domain.generic.Identity;

public class ActionId extends Identity {
	public ActionId() {
		super();
	}

	private ActionId(String value) {
		super(value);
	}

	public static ActionId of(String value) {
		return new ActionId(value);
	}
}
