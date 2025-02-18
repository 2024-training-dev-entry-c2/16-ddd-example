package com.buildingblocks.combat.domain.enemy.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class RegisteredAction extends DomainEvent {
    private final String enemyId;
    private final String actionId;
    private final String actionType;
    private final Integer objetive;
    private final Integer damage;
    private final String effectType;
    private final String result;
    private final Integer intensity;
    private final Integer duration;

    public RegisteredAction(String characterId, String actionId, String actionType, int objetive, int damage, String effectType, String result, int intensity, Integer duration) {
        super(EventsEnum.REGISTERED_ACTION.name());
        this.enemyId = characterId;
        this.actionId = actionId;
        this.actionType = actionType;
        this.objetive = objetive;
        this.damage = damage;
        this.effectType = effectType;
        this.result = result;
        this.intensity = intensity;
        this.duration = duration;
    }

    public String getEnemyId() {
        return enemyId;
    }

    public String getActionId() {
        return actionId;
    }

    public String getActionType() {
        return actionType;
    }

    public int getObjetive() {
        return objetive;
    }

    public int getDamage() {
        return damage;
    }

    public String getEffectType() {
        return effectType;
    }

    public String getResult() {
        return result;
    }

    public Integer getDuration() {
        return duration;
    }

    public int getIntensity() {
        return intensity;
    }
}
