package com.buildingblocks.shared.application.deckOfCards.addCard;

import com.buildingblocks.shared.application.Request;

public class AddCardRequest extends Request {
    private final String skillCardName;
    private final Integer initiative;
    private final String nameEffect;//atacar, curar,habiliadad
    private final Integer objetives;
    private final Integer scope;
    private final Integer impact;
    private final Integer duration;

    public AddCardRequest(String aggregateId, String skillCardName, Integer iniciative, String nameEffect, Integer objetives, Integer scope, Integer impact, Integer duration) {
        super(aggregateId);
        this.skillCardName = skillCardName;
        this.initiative = iniciative;
        this.nameEffect = nameEffect;
        this.objetives = objetives;
        this.scope = scope;
        this.impact = impact;
        this.duration = duration;
    }

    public String getSkillCardName() {
        return skillCardName;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public String getNameEffect() {
        return nameEffect;
    }

    public Integer getObjetives() {
        return objetives;
    }

    public Integer getScope() {
        return scope;
    }

    public Integer getImpact() {
        return impact;
    }

    public Integer getDuration() {
        return duration;
    }
}


