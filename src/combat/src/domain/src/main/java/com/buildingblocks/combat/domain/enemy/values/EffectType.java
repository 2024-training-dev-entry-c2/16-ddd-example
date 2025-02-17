package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class EffectType implements IValueObject {
    private final String nameEffect;//atacar, curar,habiliadad
    private final String duration;
    private final String intensity;//cantidad de daño


    private EffectType(String value, String duration, String intensity) {
        this.nameEffect = value;
        this.duration = duration;
        this.intensity = intensity;
    }

    public static EffectType of(String value, String duration,String intensity) {
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

    public String getDuration() {
        return duration;
    }

    public String getImpact() {
        return intensity;
    }
}

/*
Infligir daño: "Inflige 2 puntos de daño a un enemigo".

Curar vida: "Cura 3 puntos de vida a un aliado".

Movimiento: "Mueve 4 casillas".
 */
