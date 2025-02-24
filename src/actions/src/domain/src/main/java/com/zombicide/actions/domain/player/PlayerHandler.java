package com.zombicide.actions.domain.player;

import com.zombicide.actions.domain.player.entities.Skill;
import com.zombicide.actions.domain.player.entities.Survivor;
import com.zombicide.actions.domain.player.entities.Weapon;
import com.zombicide.actions.domain.player.events.AddedSurvivor;
import com.zombicide.actions.domain.player.events.ChangedSurvivorPosition;
import com.zombicide.actions.domain.player.events.DiscardedWeapon;
import com.zombicide.actions.domain.player.events.ObtainedWeapon;
import com.zombicide.actions.domain.player.events.ChosenSkill;
import com.zombicide.actions.domain.shared.values.Name;
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
  public PlayerHandler(Player player) {
    add(addSurvivor(player));
    add(changeSurvivorPosition(player));
    add(chooseSkill(player));
    add(obtainWeapon(player));
    add(discardWeapon(player));
  }

  public Consumer<? extends DomainEvent> addSurvivor(Player player) {
    return (AddedSurvivor event) -> {
      Survivor survivor = player.getAvailableSurvivors().stream()
        .filter(s -> s.getIdentity().getValue().equals(event.getId()))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Survivor with id " + event.getId() + " not found"));

      player.getSurvivors().add(survivor);
      player.setName(Name.of(event.getPlayerName()));
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

  public Consumer<? extends DomainEvent> chooseSkill(Player player) {
    return (ChosenSkill event) -> {
      player.getSurvivors().stream()
        .filter(s -> s.getIdentity().getValue().equals(event.getSurvivorId()))
        .findFirst()
        .ifPresent(survivor -> {
          player.getAvailableSkills().stream()
            .filter(s -> s.getIdentity().getValue().equals(event.getSkillId()))
            .findFirst()
            .ifPresent(skill -> {
              if (skill.validateUnlockPoints(survivor.getExperience())) {
                survivor.getSkills().add(skill);
              }
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
          Weapon weapon = player.getAvailableWeapons().get(randomIndex(player.getAvailableWeapons().size()));
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
