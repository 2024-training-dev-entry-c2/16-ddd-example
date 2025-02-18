package com.buildingblocks.combat.domain.deckOfCards.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class CardAdded extends DomainEvent {
    private  final String cardId;
    private final String cardName;
    private final int initiative;
    private final String effectType;
    private final int objectives;
    private final int scope;
    private  final String deckId;
    private final String intensity;
    private  final String duration;

    public CardAdded(String type, String cardId, String cardName, int initiative, String effectType, int objectives, int scope, String deckId, String intensity, String duration) {
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

    public int getInitiative() {
        return initiative;
    }

    public String getEffectType() {
        return effectType;
    }

    public int getObjectives() {
        return objectives;
    }

    public int getScope() {
        return scope;
    }

    public String getIntensity() {
        return intensity;
    }

    public String getDuration() {
        return duration;
    }
}
