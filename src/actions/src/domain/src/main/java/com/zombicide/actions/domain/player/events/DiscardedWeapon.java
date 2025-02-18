package com.zombicide.actions.domain.player.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class DiscardedWeapon extends DomainEvent {
	private final String weaponId;
	private final String survivorId;

	public DiscardedWeapon(String skillId, String survivorId) {
		super(EventsEnum.DISCARDED_WEAPON.name());
		this.weaponId = skillId;
		this.survivorId = survivorId;
	}

	public String getWeaponId() {
		return weaponId;
	}

	public String getSurvivorId() {
		return survivorId;
	}
}