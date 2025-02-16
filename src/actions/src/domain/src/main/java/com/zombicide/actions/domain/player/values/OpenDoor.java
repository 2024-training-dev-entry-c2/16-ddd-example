package com.zombicide.actions.domain.player.values;

import com.zombicide.shared.domain.generic.IValueObject;

import static com.zombicide.shared.domain.values.Validate.validateNotNull;

public class OpenDoor implements IValueObject {
	private final Boolean value;

	private OpenDoor(Boolean value) {
		this.value = value;
		validate();
	}

	public static OpenDoor of(Boolean value) {
		return new OpenDoor(value);
	}

	@Override
	public void validate() {
		validateNotNull(this.value, "The open door cant be null");
	}

	public Boolean getValue() {
		return value;
	}
}