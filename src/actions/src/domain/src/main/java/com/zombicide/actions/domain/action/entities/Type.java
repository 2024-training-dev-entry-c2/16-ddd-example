package com.zombicide.actions.domain.action.entities;

import com.zombicide.actions.domain.action.values.AmountNoise;
import com.zombicide.actions.domain.action.values.Effect;
import com.zombicide.actions.domain.action.values.TypeId;
import com.zombicide.actions.domain.player.values.IsNoisy;
import com.zombicide.actions.domain.shared.values.Description;
import com.zombicide.actions.domain.shared.values.Name;
import com.zombicide.actions.domain.shared.values.Position;
import com.zombicide.shared.domain.generic.Entity;

public class Type extends Entity<TypeId> {
	private Name name;
	private Description description;
	private Effect effect;
	private Position position;
	private AmountNoise amountNoise;

	//region Constructors
	public Type(TypeId identity, Name name, Description description, Effect effect, Position position, AmountNoise amountNoise) {
		super(identity);
		this.name = name;
		this.description = description;
		this.effect = effect;
		this.position = position;
		this.amountNoise = amountNoise;
	}

	public Type(Name name, Description description, Effect effect, Position position, AmountNoise amountNoise) {
		super(new TypeId());
		this.name = name;
		this.description = description;
		this.effect = effect;
		this.position = position;
		this.amountNoise = amountNoise;
	}
	//endregion

	//region Getters and Setters
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public AmountNoise getAmountNoise() {
		return amountNoise;
	}

	public void setAmountNoise(AmountNoise amountNoise) {
		this.amountNoise = amountNoise;
	}
	//endregion

	//region Public Methods
	public void increaseNoise() {
		this.amountNoise = AmountNoise.of(this.amountNoise.getValue() + 1);
	}

	public void resetNoise() {
		this.amountNoise = AmountNoise.of(0);
	}
	//endregion
}
