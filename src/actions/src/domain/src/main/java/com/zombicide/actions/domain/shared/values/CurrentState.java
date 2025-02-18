package com.zombicide.actions.domain.shared.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotBlank;
import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class CurrentState implements IValueObject {
	private final String value;

	private CurrentState(String value) {
		this.value = value;
		validate();
	}

	public static CurrentState of(String value) {
		return new CurrentState(value);
	}

	@Override
	public void validate() {
		validateNotNull(this.value, "The current state cant be null");
		validateNotBlank(this.value, "The current state cant be blank");
	}

	public String getValue() {
		return value;
	}
}