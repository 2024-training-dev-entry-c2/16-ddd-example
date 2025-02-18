package com.zombicide.actions.domain.player.initializer;

import com.zombicide.actions.domain.player.entities.Skill;
import com.zombicide.actions.domain.player.entities.Survivor;
import com.zombicide.actions.domain.player.entities.Weapon;
import com.zombicide.actions.domain.player.values.Dice;
import com.zombicide.actions.domain.player.values.Experience;
import com.zombicide.actions.domain.player.values.IsNoisy;
import com.zombicide.actions.domain.player.values.OpenDoor;
import com.zombicide.actions.domain.player.values.Precision;
import com.zombicide.actions.domain.player.values.Scope;
import com.zombicide.actions.domain.player.values.UnlockPoints;
import com.zombicide.actions.domain.shared.values.CurrentState;
import com.zombicide.actions.domain.shared.values.Damage;
import com.zombicide.actions.domain.shared.values.Description;
import com.zombicide.actions.domain.shared.values.Name;
import com.zombicide.actions.domain.shared.values.Position;

import java.util.Arrays;
import java.util.List;

public class Players {
	public static List<Survivor> initializrSurvivors() {
			Survivor josh = new Survivor(
				Name.of("Josh"),
				Experience.of(0),
				Position.of(0, 0),
				CurrentState.of("Sano")
			);
			josh.getSkills().add(new Skill(Name.of("Sigilo"), Description.of("Ignora a los zombis al entrar en una zona."), UnlockPoints.of(0)));
			josh.getWeapons().add(new Weapon(Name.of("Pistola"), Scope.of(0, 1), Damage.of(1), Dice.of(1), Precision.of(4), IsNoisy.of(true), OpenDoor.of(false)));


		Survivor wanda = new Survivor(
				Name.of("Wanda"),
				Experience.of(0),
				Position.of(0, 0),
				CurrentState.of("Sano")
			);
			wanda.getSkills().add(new Skill(Name.of("Velocidad"), Description.of("Puede realizar una acción adicional de movimiento."), UnlockPoints.of(0)));
			wanda.getWeapons().add(new Weapon(Name.of("Motosierra"), Scope.of(0, 0), Damage.of(2), Dice.of(5), Precision.of(5), IsNoisy.of(true), OpenDoor.of(true)));

		Survivor phil = new Survivor(
				Name.of("Phil"),
				Experience.of(0),
				Position.of(0, 0),
				CurrentState.of("Sano")
			);
			phil.getSkills().add(new Skill(Name.of("Francotirador"), Description.of("Puede elegir sus objetivos con armas de fuego."), UnlockPoints.of(0)));
			phil.getWeapons().add(new Weapon(Name.of("Escopeta"), Scope.of(0, 1), Damage.of(2), Dice.of(2), Precision.of(4), IsNoisy.of(true), OpenDoor.of(false)));

		Survivor amy = new Survivor(
				Name.of("Amy"),
				Experience.of(0),
				Position.of(0, 0),
				CurrentState.of("Sano")
			);
			amy.getSkills().add(new Skill(Name.of("Acción gratuita de combate"), Description.of("Gana una acción adicional de combate por turno."), UnlockPoints.of(0)));
			amy.getWeapons().add(new Weapon(Name.of("Hacha"), Scope.of(0, 0), Damage.of(2), Dice.of(1), Precision.of(4), IsNoisy.of(false), OpenDoor.of(true)));

		Survivor doug = new Survivor(
				Name.of("Doug"),
				Experience.of(0),
				Position.of(0, 0),
				CurrentState.of("Sano")
			);
		doug.getSkills().add(new Skill(Name.of("Dos armas"), Description.of("Puede usar dos armas a la vez, siempre que sean iguales."), UnlockPoints.of(0)));
		doug.getWeapons().add(new Weapon(Name.of("Uzi"), Scope.of(0, 1), Damage.of(1), Dice.of(3), Precision.of(5), IsNoisy.of(true), OpenDoor.of(false)));


		Survivor ned = new Survivor(
				Name.of("Ned"),
				Experience.of(0),
				Position.of(0, 0),
				CurrentState.of("Sano")
			);
		ned.getSkills().add(new Skill(Name.of("Busca gratis"), Description.of("Puede buscar en una zona sin gastar una acción."), UnlockPoints.of(0)));
		ned.getWeapons().add(new Weapon(Name.of("Palanca"), Scope.of(0, 0), Damage.of(1), Dice.of(1), Precision.of(4), IsNoisy.of(false), OpenDoor.of(true)));

		return Arrays.asList(josh, wanda, phil, amy, doug, ned);
	}
}