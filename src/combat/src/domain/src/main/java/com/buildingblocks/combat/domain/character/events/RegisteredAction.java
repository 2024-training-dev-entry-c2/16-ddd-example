package com.buildingblocks.combat.domain.character.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class RegisteredAction extends DomainEvent {
    private final String characterId;
    private final String actionId;
    private final String actionType;
    private final Integer objetive;
    private final Integer damage;
    private final String effectType;
    private final String result;
    private final Integer intensity;
    private final Integer duration;

    public RegisteredAction(String characterId, String actionId, String actionType, Integer objetive, Integer damage, String effectType, String result, Integer intensity, Integer duration) {
        super(EventsEnum.REGISTERED_ACTION.name());
        this.characterId = characterId;
        this.actionId = actionId;
        this.actionType = actionType;
        this.objetive = objetive;
        this.damage = damage;
        this.effectType = effectType;
        this.result = result;
        this.intensity = intensity;
        this.duration = duration;
    }

    public String getCharacterId() {
        return characterId;
    }

    public String getActionId() {
        return actionId;
    }

    public String getActionType() {
        return actionType;
    }

    public Integer getObjetive() {
        return objetive;
    }

    public Integer getDamage() {
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

    public Integer getIntensity() {
        return intensity;
    }
}
