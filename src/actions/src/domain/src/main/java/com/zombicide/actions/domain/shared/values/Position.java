package com.zombicide.actions.domain.shared.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotBlank;
import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class Position implements IValueObject {
	private final String value;

	private Position(String value) {
		this.value = value;
		validate();
	}

	public static Position of(String value) {
		return new Position(value);
	}

	@Override
	public void validate() {
		validateNotNull(this.value, "The position cant be null");
		validateNotBlank(this.value, "The position cant be blank");
	}

	public String getValue() {
		return value;
	}
}