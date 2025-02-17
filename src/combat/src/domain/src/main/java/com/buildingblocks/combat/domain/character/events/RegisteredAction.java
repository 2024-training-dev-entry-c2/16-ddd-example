package com.buildingblocks.combat.domain.character.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class RegisteredAction extends DomainEvent {
    private final String characterId;
    private final String actionId;
    private final String actionType;
    private final String objetive;
    private final int damage;
    private final String effectType;
    private final String result;

    public RegisteredAction(String type, String characterId, String actionId, String actionType, String objetive, int damage, String effectType, String result) {
        super(EventsEnum.REGISTERED_ACTION.name());
        this.characterId = characterId;
        this.actionId = actionId;
        this.actionType = actionType;
        this.objetive = objetive;
        this.damage = damage;
        this.effectType = effectType;
        this.result = result;
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

    public String getObjetive() {
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
}
