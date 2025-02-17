package com.buildingblocks.combat.domain.enemy.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class UsedCard  extends DomainEvent {
    private final String enemyId;
    private final String abilityId;
    private final String objetiveId;


    public UsedCard(String enemigoId, String habilidadId, String objetivoId) {
        super(EventsEnum.SKILL_USED.name());
        this.enemyId = enemigoId;
        this.abilityId = habilidadId;
        this.objetiveId = objetivoId;

    }

    public String getEnemyId() {
        return enemyId;
    }

    public String getAbilityId() {
        return abilityId;
    }

    public String getObjetiveId() {
        return objetiveId;
    }
}
