package com.zombicide.actions.domain.player.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotNegative;
import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class UnlockPoints implements IValueObject {
	private final Integer value;

	private UnlockPoints(Integer value) {
		this.value = value;
		validate();
	}

	public static UnlockPoints of(Integer value) {
		return new UnlockPoints(value);
	}

	@Override
	public void validate() {
		validateNotNegative(this.value, "The points cant be negative");
		validateNotNull(this.value, "The points cant be null");
		validateNumber(this.value);
	}

	public Integer getValue() {
		return value;
	}

	private void validateNumber(Integer value) {
		if (value > 43) {
			throw new IllegalArgumentException("The points cant be more than 43");
		}
	}
}