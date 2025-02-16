package com.zombicide.shared.domain.values;

public class Validate {
	public static void validateNotNull(String value, String text) {
		if (value == null) {
			throw new IllegalArgumentException(text);
		}
	}

	public static void validateNotBlank(String value, String text) {
		if (value.isBlank()) {
			throw new IllegalArgumentException(text);
		}
	}
}