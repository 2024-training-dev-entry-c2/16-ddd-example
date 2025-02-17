package com.buildingblocks.combat.domain.enemy.values;

import com.buildingblocks.combat.domain.character.values.StatusActivateId;
import com.buildingblocks.shared.domain.generic.Identity;

public class SkillId extends Identity {
    public SkillId() {
        super();
    }
    public SkillId(String value) {
        super(value);
    }
    public static SkillId of(String value) {
        return new SkillId(value);
    }
}
