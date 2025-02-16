package com.zombicide.actions.domain.shared.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotBlank;
import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class Name implements IValueObject {
	private final String value;

	private Name(String value) {
		this.value = value;
		validate();
	}

	public static Name of(String value) {
		return new Name(value);
	}

	@Override
	public void validate() {
		validateNotNull(this.value, "The name cant be null");
		validateNotBlank(this.value, "The name cant be blank");
	}

	public String getValue() {
		return value;
	}
}