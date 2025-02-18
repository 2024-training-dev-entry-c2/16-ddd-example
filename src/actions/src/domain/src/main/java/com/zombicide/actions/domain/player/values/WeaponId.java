package com.zombicide.actions.domain.player.values;

import com.zombicide.shared.domain.generic.Identity;

public class WeaponId extends Identity {
	public WeaponId() {
		super();
	}

	private WeaponId(String value) {
		super(value);
	}

	public static WeaponId of(String value) {
		return new WeaponId(value);
	}
}