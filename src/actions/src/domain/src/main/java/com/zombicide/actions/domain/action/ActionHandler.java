package com.zombicide.actions.domain.action;

import com.zombicide.actions.domain.action.entities.Affected;
import com.zombicide.actions.domain.action.entities.Type;
import com.zombicide.actions.domain.action.events.EliminatedZombie;
import com.zombicide.actions.domain.action.events.FoundObject;
import com.zombicide.actions.domain.action.events.InjuredSurvivor;
import com.zombicide.actions.domain.action.events.MadeAttack;
import com.zombicide.actions.domain.action.events.MadeMovement;
import com.zombicide.actions.domain.action.events.OpenedDoor;
import com.zombicide.actions.domain.action.values.AmountNoise;
import com.zombicide.actions.domain.action.values.Effect;
import com.zombicide.actions.domain.action.values.TypeAffected;
import com.zombicide.actions.domain.player.values.IsNoisy;
import com.zombicide.actions.domain.shared.values.CurrentState;
import com.zombicide.actions.domain.shared.values.Damage;
import com.zombicide.actions.domain.shared.values.Description;
import com.zombicide.actions.domain.shared.values.Name;
import com.zombicide.actions.domain.shared.values.Position;
import com.zombicide.shared.domain.generic.DomainActionsContainer;
import com.zombicide.shared.domain.generic.DomainEvent;

import java.util.function.Consumer;

public class ActionHandler extends DomainActionsContainer {
	public ActionHandler(Action action) {
		add(openDoor(action));
		add(makeAttack(action));
		add(findObject(action));
		add(makeMovement(action));
		add(injureSurvivor(action));
		add(eliminateZombie(action));
	}

	public Consumer<? extends DomainEvent> openDoor(Action action) {
		return (OpenedDoor event) -> {
			Type typeAction = new Type(
				Name.of(event.getNameAction()),
				Description.of(event.getDescription()),
				Effect.of(event.getEffect()),
				Position.of(event.getPositionX(), event.getPositionY()),
				IsNoisy.of(event.getNoisy()),
				AmountNoise.of(1)
			);
			action.setType(typeAction);
		};
	}

	public Consumer<? extends DomainEvent> makeAttack(Action action) {
		return (MadeAttack event) -> {
			Type typeAction = new Type(
				Name.of(event.getNameAction()),
				Description.of(event.getDescription()),
				Effect.of(event.getEffect()),
				Position.of(event.getPositionX(), event.getPositionY()),
				IsNoisy.of(event.getNoisy()),
				AmountNoise.of(1)
			);

			Affected affected = new Affected(
				TypeAffected.of("Superviviente"),
				Name.of(event.getNameAffected()),
				Position.of(event.getPositionX(), event.getPositionY()),
				Damage.of(event.getDamage()),
				CurrentState.of(event.getCurrentState())
			);

			action.getAffecteds().add(affected);
			action.setType(typeAction);
		};
	}

	public Consumer<? extends DomainEvent> findObject(Action action) {
		return (FoundObject event) -> {
			Type typeAction = new Type(
				Name.of(event.getNameAction()),
				Description.of(event.getDescription()),
				Effect.of(event.getEffect()),
				Position.of(event.getPositionX(), event.getPositionY()),
				IsNoisy.of(event.getNoisy()),
				AmountNoise.of(0)
			);
			action.setType(typeAction);
		};
	}

	public Consumer<? extends DomainEvent> makeMovement(Action action) {
		return (MadeMovement event) -> {
			Type typeAction = new Type(
				Name.of(event.getNameAction()),
				Description.of(event.getDescription()),
				Effect.of(event.getEffect()),
				Position.of(event.getPositionX(), event.getPositionY()),
				IsNoisy.of(event.getNoisy()),
				AmountNoise.of(0)
			);
			action.setType(typeAction);
		};
	}

	public Consumer<? extends DomainEvent> injureSurvivor(Action action) {
		return (InjuredSurvivor event) -> {
			Affected affected = new Affected(
				TypeAffected.of("Superviviente"),
				Name.of(event.getNameSurvivor()),
				Position.of(event.getPositionX(), event.getPositionY()),
				Damage.of(event.getDamage()),
				CurrentState.of(event.getCurrentState())
			);
			action.getAffecteds().add(affected);
		};
	}

	public Consumer<? extends DomainEvent> eliminateZombie(Action action) {
		return (EliminatedZombie event) -> {
			Affected affected = new Affected(
				TypeAffected.of("Zombie"),
				Name.of(event.getNameZombie()),
				Position.of(event.getPositionX(), event.getPositionY()),
				Damage.of(event.getDamage()),
				CurrentState.of(event.getCurrentState())
			);
			action.getAffecteds().add(affected);
			action.setAffecteds(action.getAffecteds());
		};
	}
}