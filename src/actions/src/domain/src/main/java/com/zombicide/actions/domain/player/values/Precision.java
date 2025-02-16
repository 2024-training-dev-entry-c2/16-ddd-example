package com.zombicide.actions.domain.player.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotNegative;
import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class Precision implements IValueObject {
	private final Integer value;

	private Precision(Integer value) {
		this.value = value;
		validate();
	}

	public static Precision of(Integer value) {
		return new Precision(value);
	}

	@Override
	public void validate() {
		validateNotNegative(this.value, "The precision cant be negative");
		validateNotNull(this.value, "The precision cant be null");
	}

	public Integer getValue() {
		return value;
	}
}