package com.zombicide.actions.domain.shared.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotBlank;
import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class Description implements IValueObject {
	private final String value;

	private Description(String value) {
		this.value = value;
		validate();
	}

	public static Description of(String value) {
		return new Description(value);
	}

	@Override
	public void validate() {
		validateNotNull(this.value, "The description cant be null");
		validateNotBlank(this.value, "The description cant be blank");
	}

	public String getValue() {
		return value;
	}
}