package com.zombicide.actions.domain.action.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotNegative;
import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class AmountNoise implements IValueObject {
	private final Integer value;

	private AmountNoise(Integer value) {
		this.value = value;
		validate();
	}

	public static AmountNoise of(Integer value) {
		return new AmountNoise(value);
	}

	@Override
	public void validate() {
		validateNotNull(this.value, "The amount noise cant be null");
		validateNotNegative(this.value, "The amount noise cant be negative");
	}

	public Integer getValue() {
		return value;
	}
}