package com.buildingblocks.combat.domain.enemy.entities;

import com.buildingblocks.combat.domain.character.values.HistoricalActionsId;
import com.buildingblocks.combat.domain.character.values.Result;
import com.buildingblocks.combat.domain.character.values.TypeAction;

public class ActionTaken {
    private TypeAction action;
    private String objetive;
    private int damage;
    private String typeEffect;
    private Result result;

    public ActionTaken(HistoricalActionsId identity, TypeAction action, String objetive, int damage, String typeEffect, Result resultado) {
        super(identity);
        this.action = action;
        this.objetive = objetive;
        this.damage = damage;
        this.typeEffect = typeEffect;
        this.result = resultado;
    }

    public ActionTaken(TypeAction action, String objetive, int damage, String typeEffect, Result resultado) {
        super(new HistoricalActionsId());
        this.action = action;
        this.objetive = objetive;
        this.damage = damage;
        this.typeEffect = typeEffect;
        this.result = resultado;
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
