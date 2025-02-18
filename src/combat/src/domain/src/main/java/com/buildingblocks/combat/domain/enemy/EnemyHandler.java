package com.buildingblocks.combat.domain.enemy;









import com.buildingblocks.combat.domain.character.values.StatusActivateId;
import com.buildingblocks.combat.domain.enemy.entities.Skill;
import com.buildingblocks.combat.domain.enemy.events.BeCured;
import com.buildingblocks.combat.domain.enemy.events.SkillAdded;
import com.buildingblocks.combat.domain.enemy.events.SufferedDamage;
import com.buildingblocks.combat.domain.enemy.events.UsedCard;
import com.buildingblocks.combat.domain.enemy.values.Health;
import com.buildingblocks.combat.domain.enemy.values.Result;

import com.buildingblocks.combat.domain.enemy.values.Scope;
import com.buildingblocks.combat.domain.enemy.values.SkillId;
import com.buildingblocks.combat.domain.enemy.values.StatusActivatedId;
import com.buildingblocks.combat.domain.enemy.values.StatusCondition;
import com.buildingblocks.combat.domain.enemy.values.TypeAction;
import com.buildingblocks.combat.domain.enemy.entities.ActionTaken;
import com.buildingblocks.combat.domain.enemy.entities.StatusActivated;
import com.buildingblocks.combat.domain.enemy.events.AppliedStatus;
import com.buildingblocks.combat.domain.enemy.events.RegisteredAction;
import com.buildingblocks.combat.domain.enemy.events.RemovedStatus;
import com.buildingblocks.combat.domain.enemy.events.TerminatedTurn;
import com.buildingblocks.combat.domain.enemy.values.Damage;
import com.buildingblocks.combat.domain.enemy.values.EffectType;
import com.buildingblocks.combat.domain.enemy.values.Impact;
import com.buildingblocks.combat.domain.enemy.values.Name;
import com.buildingblocks.combat.domain.enemy.values.Objetive;
import com.buildingblocks.combat.domain.enemy.values.RemainingTurn;
import com.buildingblocks.shared.domain.generic.DomainActionsContainer;
import com.buildingblocks.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Consumer;

public class  EnemyHandler extends DomainActionsContainer {
    public EnemyHandler(Enemy enemy){
        add(applyEffect(enemy));
        add(removeEffect(enemy));
        add(registerAction(enemy));
        add(endTurn(enemy));
        add(receiveDamage(enemy));
        add(beCured(enemy));
    }
    public Consumer<? extends DomainEvent> applyEffect(Enemy enemy) {
        return (AppliedStatus event)->{
            if (enemy.getStatusActivateds()==null){
                enemy.setStatusActivateds(new LinkedList<>());
            }
            StatusActivated effectActive = new StatusActivated(
                    new StatusActivatedId(),
                    Name.of(event.getNameEffect()),
                    Impact.of(event.getImpact()),
                    RemainingTurn.of(event.getRemainingTurns())
            );

            enemy.getStatusActivateds().add(effectActive);
            effectActive.apply();
        };
    }
    public Consumer<? extends DomainEvent> removeEffect(Enemy enemy) {
        return (RemovedStatus event)->{
            enemy.getStatusActivateds().removeIf(effect -> effect.getIdentity().equals(event.getEffectId()));
        };
    }
    public Consumer<? extends DomainEvent> registerAction(Enemy enemy) {
        return (RegisteredAction event)->{
            if (enemy.getActionTakens()==null){
                enemy.setActionTakens(new LinkedList<>());
            }
            enemy.getActionTakens().add(new ActionTaken(
                    TypeAction.of(event.getActionType()),
                    Objetive.of(event.getObjetive()),
                    Damage.of(event.getDamage()),
                    EffectType.of(event.getEffectType(),event.getIntensity(),event.getDuration()),
                    Result.of(event.getResult())

            ));
        };
    }

    public Consumer<? extends DomainEvent> endTurn(Enemy enemy) {
        return (TerminatedTurn event)->{
            enemy.getStatusActivateds().forEach(StatusActivated::reduceTurn);
            enemy.getStatusActivateds().removeIf(effect -> effect.getRemainingTurns().getValue() <= 0);
        };
    }
    public Consumer<? extends DomainEvent> receiveDamage(Enemy character) {
        return (SufferedDamage event)->{
               character.setHealth( Health.of(Math.max(character.getHealth().getValue() - event.getAmountDamage(), 0)));
        };
    }
    public Consumer<? extends DomainEvent> beCured(Enemy enemy) {
        return (BeCured event)->{
            enemy.setHealth( Health.of(enemy.getHealth().getValue() + event.getAmountCured()));
        };
    }
    public Consumer<? extends DomainEvent> useCard(Enemy enemy) {
        return (UsedCard event)->{
            Skill skill = enemy.getSkills().get(event.getAbilityId());
            skill.apply();
        };
    }
    public Consumer<? extends DomainEvent> addSkills(Enemy enemy) {
        return (SkillAdded event)->{
            if (enemy.getSkills()==null){
                enemy.setSkills(new ArrayList<>());
            }
            enemy.getSkills().add(new Skill(
                    Damage.of(event.getDamage()),
                    EffectType.of(event.getTypeEffect(),event.getDuration(),event.getIntensity()),
                    Scope.of(event.getScope()),
                    StatusCondition.of(event.getStatusCondition())
            ));
        };
    }


}
