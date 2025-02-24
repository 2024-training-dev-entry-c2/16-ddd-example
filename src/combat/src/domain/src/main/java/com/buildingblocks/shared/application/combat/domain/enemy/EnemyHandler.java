package com.buildingblocks.shared.application.combat.domain.enemy;









import com.buildingblocks.shared.application.combat.domain.enemy.entities.Skill;
import com.buildingblocks.shared.application.combat.domain.enemy.events.BeCured;
import com.buildingblocks.shared.application.combat.domain.enemy.events.SkillAdded;
import com.buildingblocks.shared.application.combat.domain.enemy.events.SufferedDamage;
import com.buildingblocks.shared.application.combat.domain.enemy.events.UsedCard;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Health;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Result;

import com.buildingblocks.shared.application.combat.domain.enemy.values.Scope;
import com.buildingblocks.shared.application.combat.domain.enemy.values.StatusCondition;
import com.buildingblocks.shared.application.combat.domain.enemy.values.TypeAction;
import com.buildingblocks.shared.application.combat.domain.enemy.entities.ActionTaken;
import com.buildingblocks.shared.application.combat.domain.enemy.entities.StatusActivated;
import com.buildingblocks.shared.application.combat.domain.enemy.events.AppliedStatus;
import com.buildingblocks.shared.application.combat.domain.enemy.events.RegisteredAction;
import com.buildingblocks.shared.application.combat.domain.enemy.events.RemovedStatus;
import com.buildingblocks.shared.application.combat.domain.enemy.events.TerminatedTurn;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Damage;
import com.buildingblocks.shared.application.combat.domain.enemy.values.EffectType;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Impact;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Name;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Objetive;
import com.buildingblocks.shared.application.combat.domain.enemy.values.RemainingTurn;
import com.buildingblocks.shared.application.shared.domain.generic.DomainActionsContainer;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

import java.util.function.Consumer;

public class  EnemyHandler extends DomainActionsContainer {
    public EnemyHandler(Enemy enemy){
        add(applyEffect(enemy));
        add(removeEffect(enemy));
        add(registerAction(enemy));
        add(endTurn(enemy));
        add(receiveDamage(enemy));
        add(beCured(enemy));
        add(useCard(enemy));
        add(addSkills(enemy));
    }
    public Consumer<? extends DomainEvent> applyEffect(Enemy enemy) {
        return (AppliedStatus event)->{
            StatusActivated effectActive = new StatusActivated(
                    Name.of(event.getNameEffect()),
                    Impact.of(event.getImpact()),
                    RemainingTurn.of(event.getRemainingTurns())
            );

            enemy.getStatusActivateds().add(effectActive);
            System.out.println(enemy.getStatusActivateds().size());
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
            System.out.println(event.getActionId());
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
            System.out.println(enemy.getStatusActivateds().get(0).getRemainingTurns().getValue());
            enemy.getStatusActivateds().removeIf(effect -> effect.getRemainingTurns().getValue() <= 0);
        };
    }
    public Consumer<? extends DomainEvent> receiveDamage(Enemy enemy) {
        return (SufferedDamage event)->{
            if (enemy.getHealth() == null) {
                enemy.setHealth( Health.of(100));
            }
               enemy.setHealth( Health.of(Math.max(enemy.getHealth().getValue() - event.getAmountDamage(), 0)));
            System.out.println(enemy.getHealth().getValue());
        };
    }
    public Consumer<? extends DomainEvent> beCured(Enemy enemy) {
        return (BeCured event)->{

            System.out.println(enemy.getHealth().getValue());

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

            enemy.getSkills().add(new Skill(
                    Damage.of(event.getDamage()),
                    EffectType.of(event.getTypeEffect(),event.getDuration(),event.getIntensity()),
                    Scope.of(event.getScope()),
                    StatusCondition.of(event.getStatusCondition())
            ));
        };
    }


}
