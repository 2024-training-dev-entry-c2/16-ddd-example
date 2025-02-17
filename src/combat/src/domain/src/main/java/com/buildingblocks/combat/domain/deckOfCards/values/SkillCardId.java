package com.buildingblocks.combat.domain.deckOfCards.values;

import com.buildingblocks.combat.domain.combat.values.GameTurnId;
import com.buildingblocks.shared.domain.generic.Identity;

public class SkillCardId extends Identity {
    public SkillCardId() {
        super();
    }

    private SkillCardId(String value) {
        super(value);
    }

    public static SkillCardId of(String value) {
        return new SkillCardId(value);
    }
}
