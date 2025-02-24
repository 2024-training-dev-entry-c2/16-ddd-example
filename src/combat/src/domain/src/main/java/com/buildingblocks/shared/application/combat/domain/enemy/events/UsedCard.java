package com.buildingblocks.shared.application.combat.domain.enemy.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class UsedCard  extends DomainEvent {
    private final String enemyId;
    private final Integer abilityId;
    private final String objetiveId;


    public UsedCard(String enemigoId, Integer abilityId, String objetivoId) {
        super(EventsEnum.SKILL_USED.name());
        this.enemyId = enemigoId;
        this.abilityId = abilityId;
        this.objetiveId = objetivoId;

    }

    public String getEnemyId() {
        return enemyId;
    }

    public Integer getAbilityId() {
        return abilityId;
    }

    public String getObjetiveId() {
        return objetiveId;
    }
}
