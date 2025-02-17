package com.buildingblocks.combat.domain.enemy;

import com.buildingblocks.combat.domain.character.entities.ActionTaken;
import com.buildingblocks.combat.domain.character.entities.StatusActivated;
import com.buildingblocks.combat.domain.enemy.entities.Skill;
import com.buildingblocks.combat.domain.enemy.values.Damage;
import com.buildingblocks.combat.domain.enemy.values.EnemyId;
import com.buildingblocks.combat.domain.enemy.values.Health;
import com.buildingblocks.combat.domain.enemy.values.Level;
import com.buildingblocks.combat.domain.enemy.values.Name;
import com.buildingblocks.combat.domain.enemy.values.TypeEnemy;
import com.buildingblocks.shared.domain.generic.AggregateRoot;

import java.util.List;

public class Character extends AggregateRoot<EnemyId> {
    private Name name;
    private TypeEnemy type;
    private Health health;
    private Damage damage;
    private Level level;
    private List<StatusActivated> statusActivateds;
    private List<ActionTaken> actionTakens;
    private List<Skill> skills;

    //region constructor

    public Character(EnemyId identity, Name name, TypeEnemy type, Health health, Damage damage, Level level, List<StatusActivated> statusActivateds, List<ActionTaken> actionTakens, List<Skill> skills) {
        super(identity);
        this.name = name;
        this.type = type;
        this.health = health;
        this.damage = damage;
        this.level = level;
        this.statusActivateds = statusActivateds;
        this.actionTakens = actionTakens;
        this.skills = skills;
    }
    public Character( Name name, TypeEnemy type, Health health, Damage damage, Level level, List<StatusActivated> statusActivateds, List<ActionTaken> actionTakens, List<Skill> skills) {
        super(new EnemyId());
        this.name = name;
        this.type = type;
        this.health = health;
        this.damage = damage;
        this.level = level;
        this.statusActivateds = statusActivateds;
        this.actionTakens = actionTakens;
        this.skills = skills;
    }

    //endregion

    //region Getter & Setter

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public TypeEnemy getType() {
        return type;
    }

    public void setType(TypeEnemy type) {
        this.type = type;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<StatusActivated> getStatusActivateds() {
        return statusActivateds;
    }

    public void setStatusActivateds(List<StatusActivated> statusActivateds) {
        this.statusActivateds = statusActivateds;
    }

    public List<ActionTaken> getActionTakens() {
        return actionTakens;
    }

    public void setActionTakens(List<ActionTaken> actionTakens) {
        this.actionTakens = actionTakens;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    //endregion

    //region DomainEvents

    //endregion

}
