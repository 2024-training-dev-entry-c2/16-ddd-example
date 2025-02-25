package com.buildingblocks.shared.application.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class CardAdded extends DomainEvent {
    private   String cardId;
    private  String cardName;
    private  Integer initiative;
    private  String effectType;
    private  Integer objectives;
    private  Integer scope;
    private  Integer intensity;
    private   Integer duration;

    public CardAdded() {
        super(EventsEnum.CARD_ADDED.name());
    }


    public CardAdded(String cardId, String cardName, Integer initiative, String effectType, Integer objectives, Integer scope , Integer intensity, Integer duration) {
        super(EventsEnum.CARD_ADDED.name());
        this.cardId = cardId;
        this.cardName = cardName;
        this.initiative = initiative;
        this.effectType = effectType;
        this.objectives = objectives;
        this.scope = scope;
        this.intensity = intensity;
        this.duration = duration;
    }

    public String getCardId() {
        return cardId;
    }


    public String getCardName() {
        return cardName;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public String getEffectType() {
        return effectType;
    }

    public Integer getObjectives() {
        return objectives;
    }

    public Integer getScope() {
        return scope;
    }

    public Integer getIntensity() {
        return intensity;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    public void setEffectType(String effectType) {
        this.effectType = effectType;
    }

    public void setObjectives(Integer objectives) {
        this.objectives = objectives;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public void setIntensity(Integer intensity) {
        this.intensity = intensity;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
