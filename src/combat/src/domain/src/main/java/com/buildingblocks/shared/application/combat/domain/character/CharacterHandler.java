package com.buildingblocks.shared.application.combat.domain.character;

import com.buildingblocks.shared.application.combat.domain.character.entities.ActionTaken;
import com.buildingblocks.shared.application.combat.domain.character.entities.StatusActivated;
import com.buildingblocks.shared.application.combat.domain.character.events.AppliedStatus;
import com.buildingblocks.shared.application.combat.domain.character.events.RegisteredAction;
import com.buildingblocks.shared.application.combat.domain.character.events.RemovedStatus;
import com.buildingblocks.shared.application.combat.domain.character.events.SufferedDamage;
import com.buildingblocks.shared.application.combat.domain.character.events.TerminatedTurn;
import com.buildingblocks.shared.application.combat.domain.character.events.beCured;
import com.buildingblocks.shared.application.combat.domain.character.values.Damage;
import com.buildingblocks.shared.application.combat.domain.character.values.EffectType;
import com.buildingblocks.shared.application.combat.domain.character.values.Health;
import com.buildingblocks.shared.application.combat.domain.character.values.Impact;
import com.buildingblocks.shared.application.combat.domain.character.values.Name;
import com.buildingblocks.shared.application.combat.domain.character.values.Objetive;
import com.buildingblocks.shared.application.combat.domain.character.values.RemainingTurn;
import com.buildingblocks.shared.application.combat.domain.character.values.Result;
import com.buildingblocks.shared.application.combat.domain.character.values.StatusActivateId;
import com.buildingblocks.shared.application.combat.domain.character.values.TypeAction;

import com.buildingblocks.shared.application.shared.domain.generic.DomainActionsContainer;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.function.Consumer;

public class CharacterHandler extends DomainActionsContainer {
    public CharacterHandler(Character character) {
        add(applyEffect(character));
        add(removeEffect(character));
        add(registerAction(character));
        add(endTurn(character));
        add(receiveDamage(character));
        add(beCured(character));
    }

    public Consumer<? extends DomainEvent> applyEffect(Character character) {
        return (AppliedStatus event) -> {
            if (character.getEffectActives() == null) {
                character.setEffectActives(new ArrayList<>());
            }
            StatusActivated effectActive = new StatusActivated(
                    new StatusActivateId(),
                    Name.of(event.getNameEffect()),
                    Impact.of(event.getImpact()),
                    RemainingTurn.of(event.getRemainingTurns())
            );

            character.getEffectActives().add(effectActive);
            effectActive.apply();
        };
    }

    public Consumer<? extends DomainEvent> removeEffect(Character character) {
        return (RemovedStatus event) -> {
            if (character.getEffectActives() == null) {
                System.out.println("Los efectos están vacíos");
            } else {
                character.getEffectActives().removeIf(effect ->
                        effect.getIdentity().getValue().equals(event.getEffectId()) // Comparar valores
                );
            }
        };
    }

    public Consumer<? extends DomainEvent> registerAction(Character character) {
        return (RegisteredAction event) -> {
            if (character.getHistoryActions() == null) {
                character.setHistoryActions(new ArrayList<>());
            }
            character.getHistoryActions().add(new ActionTaken(
                    TypeAction.of(event.getActionType()),
                    Objetive.of(event.getObjetive()),
                    Damage.of(event.getDamage()),
                    EffectType.of(event.getEffectType(), event.getDuration(), event.getIntensity()),
                    Result.of(event.getResult())
            ));
        };
    }

    public Consumer<? extends DomainEvent> endTurn(Character character) {
        return (TerminatedTurn event) -> {
            character.getEffectActives().forEach(StatusActivated::reduceTurn);
            character.getEffectActives().removeIf(effect -> effect.getRemainingTurns().getValue() <= 0);
        };
    }

    public Consumer<? extends DomainEvent> receiveDamage(Character character) {
        return (SufferedDamage event) -> {

            if (character.getHealth() == null) {
                character.setHealth(Health.of(100));
            }
            character.setHealth(Health.of(Math.max(character.getHealth().getValue() - event.getAmountDamage(), 0)));
        };
    }

    public Consumer<? extends DomainEvent> beCured(Character character) {
        return (beCured event) -> {
            character.setHealth(Health.of(character.getHealth().getValue() + event.getAmountCured()));
        };
    }
}
