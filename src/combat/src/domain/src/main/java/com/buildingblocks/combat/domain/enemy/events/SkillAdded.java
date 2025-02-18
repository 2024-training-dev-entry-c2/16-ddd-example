package com.buildingblocks.combat.domain.enemy.events;

import com.buildingblocks.combat.domain.enemy.values.Damage;
import com.buildingblocks.combat.domain.enemy.values.EffectType;
import com.buildingblocks.combat.domain.enemy.values.Scope;
import com.buildingblocks.combat.domain.enemy.values.StatusCondition;
import com.buildingblocks.shared.domain.generic.DomainEvent;

public class SkillAdded extends DomainEvent {
    private final  Integer damage;
    private final String typeEffect;
    private final Integer scope;
    private final String statusCondition;
    private final Integer duration;
    private final Integer intensity;

    public SkillAdded(Integer damage, String typeEffect, Integer scope, String statusCondition, Integer duration, Integer intensity) {
        super(EventsEnum.SKILL_ADDED.name());
        this.damage = damage;
        this.typeEffect = typeEffect;
        this.scope = scope;
        this.statusCondition = statusCondition;
        this.duration = duration;
        this.intensity = intensity;
    }

    public Integer getDamage() {
        return damage;
    }

    public String getTypeEffect() {
        return typeEffect;
    }

    public Integer getScope() {
        return scope;
    }

    public String getStatusCondition() {
        return statusCondition;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getIntensity() {
        return intensity;
    }
}
