package com.zombicide.actions.domain.action.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotBlank;
import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class TypeAffected implements IValueObject {
	private final String value;

	private TypeAffected(String value) {
		this.value = value;
		validate();
	}

	public static TypeAffected of(String value) {
		return new TypeAffected(value);
	}

	@Override
	public void validate() {
		validateNotNull(this.value, "The type affected cant be null");
		validateNotBlank(this.value, "The type affected cant be blank");
	}

	public String getValue() {
		return value;
	}
}