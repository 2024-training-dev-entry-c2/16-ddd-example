package com.buildingblocks.combat.domain.deckOfCards.values;

import com.buildingblocks.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.domain.utils.Validator;

public class MagicElement implements IValueObject {
    private final String element;
    private final String state;

    public MagicElement(String element, String state) {
        this.element = element;
        this.state = state;
    }
    public static MagicElement of(String value,String state) {
        return new MagicElement(value,state);
    }
    public static MagicElement isStrong(String value) {
        return new MagicElement(value,"STRONG");
    }
    public static MagicElement isWaning(String value) {
        return new MagicElement(value,"WANING");
    }
    public static MagicElement isInert(String value) {
        return new MagicElement(value,"INERT");
    }

    @Override
    public void validate() {
        Validator.validateTextNotNull(element);
        Validator.validateTextNotNull(state);
    }

    public String getElement() {
        return element;
    }

    public String getState() {
        return state;
    }
}
