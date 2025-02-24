package com.buildingblocks.shared.application.combat.domain.enemy.entities;




import com.buildingblocks.shared.application.combat.domain.enemy.values.Damage;
import com.buildingblocks.shared.application.combat.domain.enemy.values.EffectType;
import com.buildingblocks.shared.application.combat.domain.enemy.values.HistoricalActionsId;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Objetive;
import com.buildingblocks.shared.application.combat.domain.enemy.values.Result;
import com.buildingblocks.shared.application.combat.domain.enemy.values.TypeAction;
import com.buildingblocks.shared.application.shared.domain.generic.Entity;

public class ActionTaken extends Entity<HistoricalActionsId> {
    private TypeAction action;
    private Objetive objetive;
    private Damage damage;
    private EffectType typeEffect;
    private Result result;

    public ActionTaken(HistoricalActionsId identity, TypeAction action, Objetive objetive, Damage damage, EffectType typeEffect, Result resultado) {
        super(identity);
        this.action = action;
        this.objetive = objetive;
        this.damage = damage;
        this.typeEffect = typeEffect;
        this.result = resultado;
    }

    public ActionTaken(TypeAction action, Objetive objetive, Damage damage, EffectType typeEffect, Result resultado) {
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
