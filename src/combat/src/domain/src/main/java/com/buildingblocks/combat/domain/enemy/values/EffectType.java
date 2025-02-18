package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class EffectType implements IValueObject {
    private final String nameEffect;//atacar, curar,habiliadad
    private final int duration;
    private final int intensity;//cantidad de daño


    private EffectType(String value, int duration, int intensity) {
        this.nameEffect = value;
        this.duration = duration;
        this.intensity = intensity;
    }

    public static EffectType of(String value, int duration, int intensity) {
        EffectType effectType =  new EffectType(value, duration,intensity);
        effectType.validate();
        return effectType;
    }

    @Override
    public void validate() {
        if (nameEffect == null || nameEffect.isEmpty()) {
            throw new IllegalArgumentException("TipoEfecto cannot be null or empty.");
        }
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
