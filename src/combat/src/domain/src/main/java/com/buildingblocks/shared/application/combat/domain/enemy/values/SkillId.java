package com.buildingblocks.shared.application.combat.domain.enemy.values;

import com.buildingblocks.shared.application.shared.domain.generic.Identity;

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
