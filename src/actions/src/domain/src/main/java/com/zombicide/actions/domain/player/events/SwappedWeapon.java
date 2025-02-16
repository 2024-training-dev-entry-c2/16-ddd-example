package com.zombicide.actions.domain.player.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class SwappedWeapon extends DomainEvent {
	private final String id;
	private final String nameWeapon;
	private final Integer scope;
	private final Integer precision;
	private final Boolean isNoisy;
	private final Boolean openDoor;

	public SwappedWeapon(String id, String nameWeapon, Integer scope, Integer precision, Boolean isNoisy, Boolean openDoor) {
		super(EventsEnum.SWAPPED_WEAPON.name());
		this.id = id;
		this.nameWeapon = nameWeapon;
		this.scope = scope;
		this.precision = precision;
		this.isNoisy = isNoisy;
		this.openDoor = openDoor;
	}

	public String getId() {
		return id;
	}

	public String getNameWeapon() {
		return nameWeapon;
	}

	public Integer getScope() {
		return scope;
	}

	public Integer getPrecision() {
		return precision;
	}

	public Boolean getNoisy() {
		return isNoisy;
	}

	public Boolean getOpenDoor() {
		return openDoor;
	}
}