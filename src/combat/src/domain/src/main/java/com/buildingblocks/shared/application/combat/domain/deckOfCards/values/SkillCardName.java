package com.buildingblocks.shared.application.combat.domain.deckOfCards.values;

import com.buildingblocks.shared.application.shared.domain.generic.IValueObject;
import com.buildingblocks.shared.application.shared.domain.utils.Validator;

public class SkillCardName implements IValueObject {
    private final String value;

    private SkillCardName(String value) {
        this.value = value;
    }

    public static SkillCardName of(String value) {
        return new SkillCardName(value);
    }

    @Override
    public void validate() {
        Validator.validateTextNotNull(value);
    }

    public String getValue() {
        return value;
    }
}
