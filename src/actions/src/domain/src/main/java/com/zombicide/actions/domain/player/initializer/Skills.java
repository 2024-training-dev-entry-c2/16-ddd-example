package com.zombicide.actions.domain.player.initializer;

import com.zombicide.actions.domain.player.entities.Skill;
import com.zombicide.actions.domain.player.values.SkillId;
import com.zombicide.actions.domain.player.values.UnlockPoints;
import com.zombicide.actions.domain.shared.values.Description;
import com.zombicide.actions.domain.shared.values.Name;

import java.util.Arrays;
import java.util.List;

public class Skills {
	public static List<Skill> initializrSkills() {
		return Arrays.asList(
			new Skill(
				SkillId.of("10"),
				Name.of("Sigilo"),
				Description.of("Permite evitar que los zombis se muevan al entrar en una zona."),
				UnlockPoints.of(0)
			),
			new Skill(
				SkillId.of("20"),
				Name.of("Velocidad"),
				Description.of("Realiza una acción adicional de movimiento por turno."),
				UnlockPoints.of(11)
			),
			new Skill(
				SkillId.of("30"),
				Name.of("Francotirador"),
				Description.of("Permite elegir a los objetivos al atacar con armas de fuego."),
				UnlockPoints.of(22)
			),
			new Skill(
				SkillId.of("40"),
				Name.of("Acción gratuita de combate"),
				Description.of("Gana una acción adicional de combate por turno sin usar una acción normal."),
				UnlockPoints.of(22)
			),
			new Skill(
				SkillId.of("50"),
				Name.of("Dos armas"),
				Description.of("Permite usar dos armas en el mismo turno, siempre que sean iguales."),
				UnlockPoints.of(33)
			),
			new Skill(
				SkillId.of("60"),
				Name.of("Busca gratis"),
				Description.of("Puedes buscar sin gastar una acción."),
				UnlockPoints.of(33)
			)
		);
	}
}