package com.zombicide.actions.domain.action.events;

import com.zombicide.shared.domain.generic.DomainEvent;

public class FoundObject extends DomainEvent {
	private final String id;
	private final String nameAction;
	private final String description;
	private final String effect;
	private final String position;
	private final String isNoisy;

	public FoundObject(String id, String nameAction, String description, String effect, String position, String isNoisy) {
		super(EventsEnum.FOUND_OBJECT.name());
		this.id = id;
		this.nameAction = nameAction;
		this.description = description;
		this.effect = effect;
		this.position = position;
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

	public String getPosition() {
		return position;
	}

	public String getIsNoisy() {
		return isNoisy;
	}
}