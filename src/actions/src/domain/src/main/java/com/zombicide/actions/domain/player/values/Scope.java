package com.zombicide.actions.domain.player.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotNegative;
import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class Scope implements IValueObject {
	private final Integer min;
	private final Integer max;

	private Scope(Integer min, Integer max) {
		this.min = min;
		this.max = max;
		validate();
	}

	public static Scope of(Integer min, Integer max) {
		return new Scope(min, max);
	}

	@Override
	public void validate() {
		validateNotNegative(this.min, "The scope cant be negative");
		validateNotNull(this.min, "The scope cant be null");
		validateNotNegative(this.max, "The scope cant be negative");
		validateNotNull(this.max, "The scope cant be null");
	}

	public Integer getMin() {
		return min;
	}

	public Integer getMax() {
		return max;
	}
}