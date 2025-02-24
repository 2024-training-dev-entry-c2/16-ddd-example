package com.buildingblocks.shared.application.combat.domain.enemy.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

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
            Validator.validatePositive(valor);
        }

        public int getValor() {
            return valor;
        }
}
