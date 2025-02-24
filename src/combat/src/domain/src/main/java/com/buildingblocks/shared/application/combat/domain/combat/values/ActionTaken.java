package com.buildingblocks.shared.application.combat.domain.combat.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

import java.util.ArrayList;
import java.util.List;

public class ActionTaken implements IValueObject {
    private final List<String> actions;

    private ActionTaken(List<String> actions) {
        this.actions = actions;
    }

    public static ActionTaken of(List<String> actions) {
        ActionTaken actionTaken = new ActionTaken(actions);
        actionTaken.validate();

        return actionTaken;
    }

    public static ActionTaken empty() {
        return new ActionTaken(new ArrayList<>());
    }

    @Override
    public void validate() {
        Validator.validateNotNull(actions);
    }

    public List<String> getActions() {
        return new ArrayList<>(actions); // Devuelve una copia para mantener la inmutabilidad
    }

    public void addAction(String action) {
        if (action == null || action.trim().isEmpty()) {
            throw new IllegalArgumentException("Action cannot be null or empty.");
        }
        this.actions.add(action);
    }

    public void clearActions() {
        this.actions.clear();
    }
}
