package com.zombicide.actions.domain.shared.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotBlank;
import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class Position implements IValueObject {
	private final Integer x;
	private final Integer y;

	private Position(Integer x, Integer y) {
		this.x = x;
		this.y = y;
		validate();
	}

	public static Position of(Integer x, Integer y) {
		return new Position(x, y);
	}

	@Override
	public void validate() {
		validateNotNull(this.x, "The position cant be null");
		validateNotNull(this.y, "The position cant be null");
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}
}