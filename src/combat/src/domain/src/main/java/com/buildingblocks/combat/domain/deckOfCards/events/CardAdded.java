package com.buildingblocks.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class CardAdded extends DomainEvent {
    private  final String cardId;
    private final String cardName;
    private final Integer initiative;
    private final String effectType;
    private final Integer objectives;
    private final Integer scope;
    private  final String deckId;
    private final String intensity;
    private  final String duration;

    public CardAdded(String type, String cardId, String cardName, Integer initiative, String effectType, Integer objectives, Integer scope, String deckId, String intensity, String duration) {
        super(type);
        this.cardId = cardId;
        this.cardName = cardName;
        this.initiative = initiative;
        this.effectType = effectType;
        this.objectives = objectives;
        this.scope = scope;
        this.deckId = deckId;
        this.intensity = intensity;
        this.duration = duration;
    }

    public String getCardId() {
        return cardId;
    }

    public String getDeckId() {
        return deckId;
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

    public String getIntensity() {
        return intensity;
    }

    public String getDuration() {
        return duration;
    }
}
