package com.zombicide.actions.domain.action.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotBlank;
import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class Effect implements IValueObject {
	private final String value;

	private Effect(String value) {
		this.value = value;
		validate();
	}

	public static Effect of(String value) {
		return new Effect(value);
	}

	@Override
	public void validate() {
		validateNotNull(this.value, "The effect cant be null");
		validateNotBlank(this.value, "The effect cant be blank");
	}

	public String getValue() {
		return value;
	}
}