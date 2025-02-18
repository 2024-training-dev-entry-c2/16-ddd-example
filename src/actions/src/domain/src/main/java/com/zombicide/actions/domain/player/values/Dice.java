package com.zombicide.actions.domain.player.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotNegative;
import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class Dice implements IValueObject {
	private final Integer value;

	private Dice(Integer value) {
		this.value = value;
		validate();
	}

	public static Dice of(Integer value) {
		return new Dice(value);
	}

	@Override
	public void validate() {
		validateNotNegative(this.value, "The dice cant be negative");
		validateNotNull(this.value, "The dice cant be null");
	}

	public Integer getValue() {
		return value;
	}
}