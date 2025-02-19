package com.zombicide.actions.domain.player;

import com.zombicide.actions.domain.player.entities.Skill;
import com.zombicide.actions.domain.player.entities.Survivor;
import com.zombicide.actions.domain.player.entities.Weapon;
import com.zombicide.actions.domain.player.events.AddedSurvivor;
import com.zombicide.actions.domain.player.events.ChangedSurvivorPosition;
import com.zombicide.actions.domain.player.events.ChosenSkill;
import com.zombicide.actions.domain.player.events.DiscardedWeapon;
import com.zombicide.actions.domain.player.events.ObtainedWeapon;
import com.zombicide.actions.domain.player.events.UnlockedSkill;
import com.zombicide.actions.domain.shared.values.Position;
import com.zombicide.shared.domain.generic.DomainActionsContainer;
import com.zombicide.shared.domain.generic.DomainEvent;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import static com.zombicide.actions.domain.player.initializer.Players.initializrSurvivors;
import static com.zombicide.actions.domain.player.initializer.Skills.initializrSkills;
import static com.zombicide.actions.domain.player.initializer.Weapons.initializrWeapons;

public class PlayerHandler extends DomainActionsContainer {
	private final List<Survivor> survivors;
	private final List<Skill> skills;
	private final List<Weapon> weapons;

	public PlayerHandler(Player player) {
		survivors = initializrSurvivors();
		skills = initializrSkills();
		weapons = initializrWeapons();
		add(addSurvivor(player));
		add(changeSurvivorPosition(player));
		add(unlockSkill(player));
		add(choseSkill(player));
		add(obtainWeapon(player));
		add(discardWeapon(player));
	}

	public Consumer<? extends DomainEvent> addSurvivor(Player player) {
		return (AddedSurvivor event) -> {
			Survivor survivor = this.survivors.stream()
				.filter(s -> s.getIdentity().getValue().equals(event.getId()))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Survivor with id " + event.getId() + " not found"));

			player.getSurvivors().add(survivor);
			player.setSurvivors(player.getSurvivors());
			player.setName(event.getPlayerName());
		};
	}

	public Consumer<? extends DomainEvent> changeSurvivorPosition(Player player) {
		return (ChangedSurvivorPosition event) -> {
			player.getSurvivors().stream()
				.filter(s -> s.getIdentity().getValue().equals(event.getId()))
				.findFirst()
				.ifPresent(survivor -> {
					survivor.changePosition(Position.of(event.getPositionX(), event.getPositionY()));
				});
		};
	}

	public Consumer<? extends DomainEvent> unlockSkill(Player player) {
		return (UnlockedSkill event) -> {
			player.getSurvivors().stream()
				.filter(s -> s.getIdentity().getValue().equals(event.getSurvivorId()))
				.findFirst()
				.ifPresent(survivor -> {
					Skill skill = skills.get(randomIndex(skills.size()));
					if (skill.validateUnlockPoints(survivor.getExperience())) {
						survivor.getSkills().add(skill);
					}
				});
		};
	}

	public Consumer<? extends DomainEvent> choseSkill(Player player) {
		return (ChosenSkill event) -> {
			player.getSurvivors().stream()
				.filter(s -> s.getIdentity().getValue().equals(event.getSurvivorId()))
				.findFirst()
				.ifPresent(survivor -> {
					this.skills.stream()
						.filter(s -> s.getIdentity().getValue().equals(event.getSkillId()))
						.findFirst()
						.ifPresent(skill -> {
							survivor.getSkills().add(skill);
						});
				});
		};
	}

	public Consumer<? extends DomainEvent> obtainWeapon(Player player) {
		return (ObtainedWeapon event) -> {
			player.getSurvivors().stream()
				.filter(s -> s.getIdentity().getValue().equals(event.getId()))
				.findFirst()
				.ifPresent(survivor -> {
					Weapon weapon = this.weapons.get(randomIndex(weapons.size()));
					survivor.getWeapons().add(weapon);
				});
		};
	}

	public Consumer<? extends DomainEvent> discardWeapon(Player player) {
		return (DiscardedWeapon event) -> {
			player.getSurvivors().stream()
				.filter(s -> s.getIdentity().getValue().equals(event.getSurvivorId()))
				.findFirst()
				.ifPresent(survivor -> {
					survivor.getWeapons().removeIf(weapon -> weapon.getIdentity().getValue().equals(event.getWeaponId()));
				});
		};
	}

	private int randomIndex(int size) {
		Random random = new Random();
		return random.nextInt(size);
	}
}
