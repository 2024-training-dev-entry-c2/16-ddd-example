package com.zombicide.actions.domain.action.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class IsSuccessful implements IValueObject {
	private final Boolean value;

	private IsSuccessful(Boolean value) {
		this.value = value;
		validate();
	}

	public static IsSuccessful of(Boolean value) {
		return new IsSuccessful(value);
	}

	@Override
	public void validate() {
		validateNotNull(this.value, "The effect cant be null");
	}

	public Boolean getValue() {
		return value;
	}
}