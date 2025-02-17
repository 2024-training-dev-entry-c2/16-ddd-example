package com.buildingblocks.combat.domain.combat.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

public class CombatStatus implements IValueObject {
    private final String state;

    private CombatStatus(String state) {
        this.state = state;
    }
    public static CombatStatus of(String state){
        return new CombatStatus(state);
    }
    public static CombatStatus inProgress(){
        return new CombatStatus("EN_CURSO");
    }
    public static CombatStatus finished(){
        return new CombatStatus("TERMINADO");
    }

    @Override
    public void validate() {
        if (this.state == null) {
            throw new IllegalArgumentException("the stateCombat cant be null");
        }
        if (this.state.isBlank()) {
            throw new IllegalArgumentException("the stateCombat cant be black");
        }
    }
    @Override
    public String toString() {
        return "EstadoCombate{" +
                "estado='" + state + '\'' +
                '}';
    }

    public String getState() {
        return state;
    }
}
