package com.buildingblocks.shared.application.combat.domain.enemy.entities;

import com.buildingblocks.shared.application.combat.domain.enemy.values.Damage;
import com.buildingblocks.shared.application.combat.domain.enemy.values.EffectType;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Scope;
import com.buildingblocks.shared.application.combat.domain.enemy.values.SkillId;
import com.buildingblocks.shared.application.combat.domain.enemy.values.StatusCondition;
import com.buildingblocks.shared.application.shared.domain.generic.Entity;

import java.util.Objects;

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
    public void apply(){
        System.out.println("tipo de efecto:"+typeEffect);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(damage, skill.damage) && Objects.equals(typeEffect, skill.typeEffect) && Objects.equals(scope, skill.scope) && Objects.equals(statusCondition, skill.statusCondition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(damage, typeEffect, scope, statusCondition);
    }
}
