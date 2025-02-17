package com.buildingblocks.combat.domain.deckOfCards.values;

import com.buildingblocks.shared.domain.generic.IValueObject;

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
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("SkillCardName cannot be null or empty.");
        }
    }

    public String getValue() {
        return value;
    }
}
