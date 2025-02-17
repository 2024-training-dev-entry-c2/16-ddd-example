package com.buildingblocks.combat.domain.enemy.entities;

import com.buildingblocks.combat.domain.enemy.values.Damage;
import com.buildingblocks.combat.domain.enemy.values.EffectType;
import com.buildingblocks.combat.domain.enemy.values.Scope;
import com.buildingblocks.combat.domain.enemy.values.SkillId;
import com.buildingblocks.combat.domain.enemy.values.StatusCondition;
import com.buildingblocks.shared.domain.generic.Entity;
import com.buildingblocks.shared.domain.generic.Identity;

public class Skill extends Entity<SkillId> {
    private Damage damage;
    private EffectType typeEffect;
    private Scope scope;
    private StatusCondition statusCondition;

    public Skill(Damage damage, EffectType typeEffect, Scope scope, StatusCondition statusCondition) {
        super(new SkillId() );
        this.damage = damage;
        this.typeEffect = typeEffect;
        this.scope = scope;
        this.statusCondition = statusCondition;
    }

    public Skill(SkillId value, Damage damage, EffectType typeEffect, Scope scope, StatusCondition statusCondition) {
        super(value);
        this.damage = damage;
        this.typeEffect = typeEffect;
        this.scope = scope;
        this.statusCondition = statusCondition;
    }

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    public EffectType getTypeEffect() {
        return typeEffect;
    }

    public void setTypeEffect(EffectType typeEffect) {
        this.typeEffect = typeEffect;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public StatusCondition getStatusCondition() {
        return statusCondition;
    }

    public void setStatusCondition(StatusCondition statusCondition) {
        this.statusCondition = statusCondition;
    }
}
