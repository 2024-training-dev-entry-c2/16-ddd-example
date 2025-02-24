package com.buildingblocks.shared.application.combat.domain.combat.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

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
        Validator.validateNotNull(state);
        Validator.validateNotBlank(state);

    }
    @Override
    public String toString() {
        return "EstadoCombate{" +
                "estado='" + state + '\'' +
                '}';
    }

    public String getValue() {
        return state;
    }
}
