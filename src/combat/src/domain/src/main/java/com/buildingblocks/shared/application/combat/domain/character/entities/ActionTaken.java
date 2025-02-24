package com.buildingblocks.shared.application.combat.domain.character.entities;

import com.buildingblocks.shared.application.combat.domain.character.values.Damage;
import com.buildingblocks.shared.application.combat.domain.character.values.EffectType;
import com.buildingblocks.shared.application.combat.domain.character.values.Objetive;
import com.buildingblocks.shared.application.combat.domain.character.values.Result;
import com.buildingblocks.shared.application.combat.domain.character.values.TypeAction;

import com.buildingblocks.shared.application.combat.domain.enemy.values.ActionTakenId;
import com.buildingblocks.shared.application.shared.domain.generic.Entity;

public class ActionTaken extends Entity<ActionTakenId> {
    private TypeAction action;
    private Objetive objetive;
    private Damage damage;
    private EffectType typeEffect;
    private Result result;

    public ActionTaken(ActionTakenId identity, TypeAction action, Objetive objetive, Damage damage, EffectType typeEffect, Result result) {
        super(identity);
        this.action = action;
        this.objetive = objetive;
        this.damage = damage;
        this.typeEffect = typeEffect;
        this.result = result;
    }

    public ActionTaken(TypeAction action, Objetive objetive, Damage damage, EffectType typeEffect, Result result) {
        super(new ActionTakenId());
        this.action = action;
        this.objetive = objetive;
        this.damage = damage;
        this.typeEffect = typeEffect;
        this.result = result;
    }

    public TypeAction getAction() {
        return action;
    }

    public void setAction(TypeAction action) {
        this.action = action;
    }

    public Objetive getObjetive() {
        return objetive;
    }

    public void setObjetive(Objetive objetive) {
        this.objetive = objetive;
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void registerAction() {
        System.out.println("Acción registrada: " + action.getValue() + " sobre " + objetive + ". Daño: " + damage);
    }
}
