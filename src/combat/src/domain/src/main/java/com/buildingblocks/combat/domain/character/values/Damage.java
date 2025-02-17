package com.buildingblocks.combat.domain.character.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class Damage  implements IValueObject {
    private final int valor;

    private Damage(int valor) {
        this.valor = valor;
    }

    public static Damage of(int valor) {
        return new Damage(valor);
    }

    @Override
    public void validate() {
        if (valor < 0) {
            throw new IllegalArgumentException("El daño no puede ser negativo.");
        }
    }

    public int getValor() {
        return valor;
    }
}
