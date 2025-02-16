package com.zombicide.actions.domain.player.entities;

import com.zombicide.actions.domain.player.values.Dice;
import com.zombicide.actions.domain.player.values.IsNoisy;
import com.zombicide.actions.domain.player.values.OpenDoor;
import com.zombicide.actions.domain.player.values.Precision;
import com.zombicide.actions.domain.player.values.Scope;
import com.zombicide.actions.domain.player.values.WeaponId;
import com.zombicide.actions.domain.shared.values.Damage;
import com.zombicide.actions.domain.shared.values.Name;
import com.zombicide.shared.domain.generic.Entity;

public class Weapon extends Entity<WeaponId> {
	private Name name;
	private Scope scope;
	private Damage damage;
	private Dice dice;
	private Precision precision;
	private IsNoisy isNoisy;
	private OpenDoor openDoor;

	//region Constructors
	public Weapon(WeaponId identity, Name name, Scope scope, Damage damage, Dice dice, Precision precision, IsNoisy isNoisy, OpenDoor openDoor) {
		super(identity);
		this.name = name;
		this.scope = scope;
		this.damage = damage;
		this.dice = dice;
		this.precision = precision;
		this.isNoisy = isNoisy;
		this.openDoor = openDoor;
	}

	public Weapon(Name name, Scope scope, Damage damage, Dice dice, Precision precision, IsNoisy isNoisy, OpenDoor openDoor) {
		super(new WeaponId());
		this.name = name;
		this.scope = scope;
		this.damage = damage;
		this.dice = dice;
		this.precision = precision;
		this.isNoisy = isNoisy;
		this.openDoor = openDoor;
	}
	//endregion

	//region Getters and Setters
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	public Damage getDamage() {
		return damage;
	}

	public void setDamage(Damage damage) {
		this.damage = damage;
	}

	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		this.dice = dice;
	}

	public Precision getPrecision() {
		return precision;
	}

	public void setPrecision(Precision precision) {
		this.precision = precision;
	}

	public IsNoisy getIsNoisy() {
		return isNoisy;
	}

	public void setIsNoisy(IsNoisy isNoisy) {
		this.isNoisy = isNoisy;
	}

	public OpenDoor getOpenDoor() {
		return openDoor;
	}

	public void setOpenDoor(OpenDoor openDoor) {
		this.openDoor = openDoor;
	}
	//endregion

	//region Domain Actions
	public int rollDice() {
		return (int) (Math.random() * 6) + 1;
	}

	public String validatePrecision() {
		return this.precision.getValue() == rollDice()
			? "The precision is valid"
			: "The precision is invalid";
	}
	//endregion
}
