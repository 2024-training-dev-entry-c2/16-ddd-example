package com.buildingblocks.shared.application.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class CardAdded extends DomainEvent {
    private  final String cardId;
    private final String cardName;
    private final Integer initiative;
    private final String effectType;
    private final Integer objectives;
    private final Integer scope;
    private final Integer intensity;
    private  final Integer duration;
    public CardAdded( String cardId, String cardName, Integer initiative, String effectType, Integer objectives, Integer scope , Integer intensity, Integer duration) {
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
}
