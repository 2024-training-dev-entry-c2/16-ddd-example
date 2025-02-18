package com.buildingblocks.combat.domain.deckOfCards.entities;

import com.buildingblocks.combat.domain.deckOfCards.values.EffectType;
import com.buildingblocks.combat.domain.deckOfCards.values.Enhanced;
import com.buildingblocks.combat.domain.deckOfCards.values.InitiativeCard;
import com.buildingblocks.combat.domain.deckOfCards.values.MagicElement;
import com.buildingblocks.combat.domain.deckOfCards.values.Objetives;
import com.buildingblocks.combat.domain.deckOfCards.values.Scope;
import com.buildingblocks.combat.domain.deckOfCards.values.SkillCardId;
import com.buildingblocks.combat.domain.deckOfCards.values.SkillCardName;
import com.buildingblocks.combat.domain.deckOfCards.values.StatusCondition;
import com.buildingblocks.shared.domain.generic.Entity;

public class SkillCard extends Entity<SkillCardId> {
    private SkillCardName skillCardName;
    private InitiativeCard iniciative;
    private EffectType effectType;
    private MagicElement magicElement;
    private Objetives objetives;
    private Scope scope;
    private StatusCondition statusCondition;
    private Enhanced isEnhanced;

    public SkillCard(SkillCardId identity, SkillCardName skillCardName, InitiativeCard initiative, EffectType effectType, Objetives objetives, Scope scope) {
        super(identity);
        this.skillCardName = skillCardName;
        this.iniciative = initiative;
        this.effectType = effectType;
        this.objetives = objetives;
        this.scope = scope;
    }

    public SkillCard(SkillCardId identity, SkillCardName nameSkill, InitiativeCard initiative, EffectType typeEffect, MagicElement element, Objetives objectives, Scope scope, StatusCondition statusCondition) {
        super(identity);
        this.skillCardName = nameSkill;
        this.iniciative = initiative;
        this.effectType = typeEffect;
        this.magicElement = element;
        this.objetives = objectives;
        this.scope = scope;
        this.statusCondition = statusCondition;
    }

    public SkillCard(SkillCardName nameSkill, InitiativeCard initiative, EffectType typeEffect, MagicElement elements, Objetives objectives, Scope scope, StatusCondition statusCondition) {
        super(new SkillCardId());
        this.skillCardName = nameSkill;
        this.iniciative = initiative;
        this.effectType = typeEffect;
        this.magicElement = elements;
        this.objetives = objectives;
        this.scope = scope;
        this.statusCondition = statusCondition;
    }

    //region methods
    public void useCard(String objetiveId) {
        if (objetives.isValidTarget()) {
            applyEffect(objetiveId);
            if (statusCondition != null) {
                applyStatusCondition(objetiveId);
            }
            if (magicElement != null) {
                generateElement();
            }
        } else {
            throw new IllegalArgumentException("Objetivo no válido para la carta: " + skillCardName.getValue());
        }
    }

    private void applyStatusCondition(String objetiveId) {
        System.out.println("Aplicando condición de estado: " + statusCondition.getValue() + " al objetivo " + objetiveId);
    }

    public void applyEffect(String objetiveId) {
        switch (effectType.getNameEffect()) {
            case "Daño":
                System.out.println("Aplicando daño de " + effectType.getNameEffect() + " al objetivo " + objetiveId);
                break;
            case "Curación":
                System.out.println("Aplicando curación de " + effectType.getNameEffect() + " al objetivo " + objetiveId);
                break;
            case "Escudo":
                System.out.println("Aplicando escudo de " + effectType.getNameEffect() + " al objetivo " + objetiveId);
                break;
            default:
                throw new IllegalArgumentException("Tipo de efecto no válido: " + effectType.getNameEffect());
        }
    }

    public void removeEffect(String objetiveId) {
        System.out.println("El efecto de la carta " + skillCardName.getValue() + " ha expirado para el objetivo " + objetiveId);
    }

    public void generateElement() {
        System.out.println("Generando elemento: " + magicElement.getElement());// Lógica para activar el elemento en el tablero
        magicElement = MagicElement.of(magicElement.getElement(), "Activo");
    }

    //endregion
    //region getter & setter
    public SkillCardName getSkillCardName() {
        return skillCardName;
    }

    public void setSkillCardName(SkillCardName skillCardName) {
        this.skillCardName = skillCardName;
    }

    public InitiativeCard getIniciative() {
        return iniciative;
    }

    public void setIniciative(InitiativeCard iniciative) {
        this.iniciative = iniciative;
    }

    public EffectType getEffectType() {
        return effectType;
    }

    public void setEffectType(EffectType effectType) {
        this.effectType = effectType;
    }

    public MagicElement getMagicElement() {
        return magicElement;
    }

    public void setMagicElement(MagicElement magicElement) {
        this.magicElement = magicElement;
    }

    public Objetives getObjetives() {
        return objetives;
    }

    public void setObjetives(Objetives objetives) {
        this.objetives = objetives;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public StatusCondition getStatusCondition() {
        return statusCondition;
    }

    public void setStatusCondition(StatusCondition statusCondition) {
        this.statusCondition = statusCondition;
    }

    public Enhanced isEnhanced() {
        return isEnhanced;
    }

    public void setEnhanced(Enhanced enhanced) {
        isEnhanced = enhanced;
    }

    //endregion
}
