package com.zombicide.actions.domain.player.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class IsNoisy implements IValueObject {
	private final Boolean value;

	private IsNoisy(Boolean value) {
		this.value = value;
		validate();
	}

	public static IsNoisy of(Boolean value) {
		return new IsNoisy(value);
	}

	@Override
	public void validate() {
		validateNotNull(this.value, "The is noisy cant be null");
	}

	public Boolean getValue() {
		return value;
	}
}