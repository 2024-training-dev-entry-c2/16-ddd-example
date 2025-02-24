package com.buildingblocks.shared.application.combat.domain.enemy.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

public class EffectType implements IValueObject {
    private final String nameEffect;//atacar, curar,habiliadad
    private final Integer duration;
    private final Integer intensity;//cantidad de daño


    private EffectType(String value, Integer duration, Integer intensity) {
        this.nameEffect = value;
        this.duration = duration;
        this.intensity = intensity;
    }

    public static EffectType of(String value, Integer duration, Integer intensity) {
        EffectType effectType =  new EffectType(value, duration,intensity);
        effectType.validate();
        return effectType;
    }

    @Override
    public void validate() {
        Validator.validateTextNotNull(nameEffect);
        Validator.validatePositive(duration);
        Validator.validatePositive(intensity);

    }

    public String getNameEffect() {
        return nameEffect;
    }

    public int getDuration() {
        return duration;
    }

    public int getImpact() {
        return intensity;
    }
}

/*
Infligir daño: "Inflige 2 puntos de daño a un enemigo".

Curar vida: "Cura 3 puntos de vida a un aliado".

Movimiento: "Mueve 4 casillas".
 */
