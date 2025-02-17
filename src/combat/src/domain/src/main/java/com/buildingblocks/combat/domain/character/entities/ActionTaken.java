package com.buildingblocks.combat.domain.character.entities;

import com.buildingblocks.combat.domain.character.values.HistoricalActionsId;
import com.buildingblocks.combat.domain.character.values.Result;
import com.buildingblocks.combat.domain.character.values.TypeAction;
import com.buildingblocks.combat.domain.enemy.values.ActionTakenId;
import com.buildingblocks.shared.domain.generic.Entity;

public class ActionTaken extends Entity<ActionTakenId> {
    private TypeAction action;
    private String objetive;
    private int damage;
    private String typeEffect;
    private Result result;

    public ActionTaken(ActionTakenId identity, TypeAction action, String objetive, int damage, String typeEffect, Result result) {
        super(identity);
        this.action = action;
        this.objetive = objetive;
        this.damage = damage;
        this.typeEffect = typeEffect;
        this.result = result;
    }

    public ActionTaken(TypeAction action, String objetive, int damage, String typeEffect, Result result) {
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

    public String getObjetive() {
        return objetive;
    }

    public void setObjetive(String objetive) {
        this.objetive = objetive;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getTypeEffect() {
        return typeEffect;
    }

    public void setTypeEffect(String typeEffect) {
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
