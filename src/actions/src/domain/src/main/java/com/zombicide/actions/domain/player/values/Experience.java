package com.zombicide.actions.domain.player.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotNegative;
import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class Experience implements IValueObject {
	private final Integer value;

	private Experience(Integer value) {
		this.value = value;
		validate();
	}

	public static Experience of(Integer value) {
		return new Experience(value);
	}

	@Override
	public void validate() {
		validateNotNegative(this.value, "The experience cant be negative");
		validateNotNull(this.value, "The experience cant be null");
	}

	public Integer getValue() {
		return value;
	}
}