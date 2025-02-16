package com.zombicide.actions.domain.action.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class MadeAttack extends DomainEvent {
	private final String id;
	private final String nameAction;
	private final String description;
	private final String effect;
	private final String typeAffectedId;
	private final String nameAffected;
	private final String positionAffected;
	private final String damage;
	private final String currentState;
	private final String isNoisy;

	public MadeAttack(String id, String nameAction, String description, String effect, String typeAffectedId, String nameAffected, String positionAffected, String damage, String currentState, String isNoisy) {
		super(EventsEnum.MADE_ATTACK.name());
		this.id = id;
		this.nameAction = nameAction;
		this.description = description;
		this.effect = effect;
		this.typeAffectedId = typeAffectedId;
		this.nameAffected = nameAffected;
		this.positionAffected = positionAffected;
		this.damage = damage;
		this.currentState = currentState;
		this.isNoisy = isNoisy;
	}

	public String getId() {
		return id;
	}

	public String getNameAction() {
		return nameAction;
	}

	public String getDescription() {
		return description;
	}

	public String getEffect() {
		return effect;
	}

	public String getTypeAffectedId() {
		return typeAffectedId;
	}

	public String getNameAffected() {
		return nameAffected;
	}

	public String getPositionAffected() {
		return positionAffected;
	}

	public String getDamage() {
		return damage;
	}

	public String getCurrentState() {
		return currentState;
	}

	public String getIsNoisy() {
		return isNoisy;
	}
}