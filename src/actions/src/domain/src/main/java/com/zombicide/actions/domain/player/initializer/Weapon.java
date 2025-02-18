package com.zombicide.actions.domain.player.initializer;

import com.zombicide.actions.domain.player.values.Dice;
import com.zombicide.actions.domain.player.values.IsNoisy;
import com.zombicide.actions.domain.player.values.OpenDoor;
import com.zombicide.actions.domain.player.values.Precision;
import com.zombicide.actions.domain.player.values.Scope;
import com.zombicide.actions.domain.shared.values.Damage;
import com.zombicide.actions.domain.shared.values.Name;

import java.util.Arrays;
import java.util.List;

public class Weapon {
	public static List<com.zombicide.actions.domain.player.entities.Weapon> initializrWeapons() {
		return Arrays.asList(
			new com.zombicide.actions.domain.player.entities.Weapon(
				Name.of("Pistola"),
				Scope.of(0, 1),
				Damage.of(1),
				Dice.of(1),
				Precision.of(4),
				IsNoisy.of(true),
				OpenDoor.of(false)
			),
			new com.zombicide.actions.domain.player.entities.Weapon(
				Name.of("Motosierra"),
				Scope.of(0, 0),
				Damage.of(2),
				Dice.of(5),
				Precision.of(5),
				IsNoisy.of(true),
				OpenDoor.of(true)
			),
			new com.zombicide.actions.domain.player.entities.Weapon(
				Name.of("Escopeta"),
				Scope.of(0, 1),
				Damage.of(2),
				Dice.of(2),
				Precision.of(4),
				IsNoisy.of(true),
				OpenDoor.of(false)
			)
		);
	}
}
