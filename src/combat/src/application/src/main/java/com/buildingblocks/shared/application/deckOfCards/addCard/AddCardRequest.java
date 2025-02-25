package com.buildingblocks.shared.application.deckOfCards.addCard;

import com.buildingblocks.shared.application.Request;

public class AddCardRequest extends Request {
    private  String cardId;
    private  String skillCardName;
    private  Integer initiative;
    private  String nameEffect;//atacar, curar,habiliadad
    private  Integer objetives;
    private  Integer scope;
    private  Integer impact;
    private  Integer duration;

    public AddCardRequest() {
        super(null);
    }

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

    public AddCardRequest(String aggregateId, String cardId, String skillCardName, Integer initiative, String nameEffect, Integer objetives, Integer scope, Integer impact, Integer duration) {
        super(aggregateId);
        this.cardId = cardId;
        this.skillCardName = skillCardName;
        this.initiative = initiative;
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

    public void setSkillCardName(String skillCardName) {
        this.skillCardName = skillCardName;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }

    public void setNameEffect(String nameEffect) {
        this.nameEffect = nameEffect;
    }

    public void setObjetives(Integer objetives) {
        this.objetives = objetives;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public void setImpact(Integer impact) {
        this.impact = impact;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}



