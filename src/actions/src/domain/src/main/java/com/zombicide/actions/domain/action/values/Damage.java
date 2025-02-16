package com.zombicide.actions.domain.action.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotNegative;
import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class Damage implements IValueObject {
	private final Integer value;

	private Damage(Integer value) {
		this.value = value;
		validate();
	}

	public static Damage of(Integer value) {
		return new Damage(value);
	}

	@Override
	public void validate() {
		validateNotNegative(this.value, "The damage cant be negative");
		validateNotNull(this.value, "The damage cant be null");
	}

	public Integer getValue() {
		return value;
	}
}