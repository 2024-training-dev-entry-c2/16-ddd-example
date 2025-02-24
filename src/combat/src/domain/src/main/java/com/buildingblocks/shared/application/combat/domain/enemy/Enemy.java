package com.buildingblocks.shared.application.combat.domain.enemy;


import com.buildingblocks.shared.application.combat.domain.character.values.StatusActivateId;
import com.buildingblocks.shared.application.combat.domain.enemy.entities.ActionTaken;
import com.buildingblocks.shared.application.combat.domain.enemy.entities.Skill;
import com.buildingblocks.shared.application.combat.domain.enemy.entities.StatusActivated;
import com.buildingblocks.shared.application.combat.domain.enemy.events.AppliedStatus;
import com.buildingblocks.shared.application.combat.domain.enemy.events.BeCured;
import com.buildingblocks.shared.application.combat.domain.enemy.events.RegisteredAction;
import com.buildingblocks.shared.application.combat.domain.enemy.events.RemovedStatus;
import com.buildingblocks.shared.application.combat.domain.enemy.events.SkillAdded;
import com.buildingblocks.shared.application.combat.domain.enemy.events.SufferedDamage;
import com.buildingblocks.shared.application.combat.domain.enemy.events.TerminatedTurn;
import com.buildingblocks.shared.application.combat.domain.enemy.events.UsedCard;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Damage;
import com.buildingblocks.shared.application.combat.domain.enemy.values.EnemyId;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Health;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Level;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Name;
import com.buildingblocks.shared.application.combat.domain.enemy.values.TypeEnemy;
import com.buildingblocks.shared.application.shared.domain.generic.AggregateRoot;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends AggregateRoot<EnemyId> {
    private Name name;
    private TypeEnemy type;
    private Health health;
    private Damage damage;
    private Level level;
    private List<StatusActivated> statusActivateds;
    private List<ActionTaken> actionTakens;
    private List<Skill> skills;

    //region constructor

    private Enemy(EnemyId identity) {
        super(identity);
        subscribe(new EnemyHandler(this));
        statusActivateds= new ArrayList<>();
        actionTakens= new ArrayList<>();
        skills= new ArrayList<>();
    }

    public Enemy() {
        super(new EnemyId());
        subscribe(new EnemyHandler(this));
        statusActivateds= new ArrayList<>();
        actionTakens= new ArrayList<>();
        skills= new ArrayList<>();
    }


    //endregion

    //region Getter & Setter



    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }


    public List<StatusActivated> getStatusActivateds() {
        return statusActivateds;
    }



    public List<ActionTaken> getActionTakens() {
        return actionTakens;
    }



    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    //endregion

    //region DomainEvents
    public void sufferDamage(int amount) {
        apply(new SufferedDamage(this.getIdentity().getValue(), amount));

    }

    public void beCured(int amount) {
        System.out.println(amount);

        apply(new BeCured(this.getIdentity().getValue(), amount));
    }

    public void applyState(StatusActivated state) {

        apply(new AppliedStatus(this.getIdentity().getValue(),
                new StatusActivateId().getValue(),
                state.getName().getValue(),
                state.getImpact().getImpact(),
                state.getRemainingTurns().getValue()));
    }

    public void removeState(String stateId) {
        apply(new RemovedStatus(this.getIdentity().getValue(), stateId));
    }

    public void actionRegister(ActionTaken accion) {
        System.out.println(accion.getAction().getValue());
        apply(new RegisteredAction(this.getIdentity().getValue(),
                accion.getAction().getValue(),
                accion.getAction().getValue(),
                accion.getObjetive().getValue(),
                accion.getDamage().getValor(),
                accion.getTypeEffect().getNameEffect(),
                accion.getResult().getValue(),
                accion.getTypeEffect().getImpact(),
                accion.getTypeEffect().getDuration()
        ));
    }

    public void endturn() {

        apply(new TerminatedTurn(this.getIdentity().getValue()));
    }

    public void useCard(Integer skillId, String enemyId) {
        apply(new UsedCard(this.getIdentity().getValue(), skillId, enemyId));
    }

    public void addSkill(Skill skill) {
        apply(new SkillAdded(skill.getDamage().getValor(),
                skill.getTypeEffect().getNameEffect(),
                skill.getScope().getValue(),
                skill.getStatusCondition().getValue(),
                skill.getTypeEffect().getDuration(),
                skill.getTypeEffect().getImpact()
        ));
    }

    public  static  Enemy from(final String identity, final List<DomainEvent> events){
        Enemy enemy = new Enemy(EnemyId.of(identity));
        events.forEach(enemy::apply);
        return enemy;
    }
    //endregion

}
