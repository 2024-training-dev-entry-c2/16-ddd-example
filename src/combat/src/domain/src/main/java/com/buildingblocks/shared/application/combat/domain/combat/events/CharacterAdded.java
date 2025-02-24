package com.buildingblocks.shared.application.combat.domain.combat.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class CharacterAdded extends DomainEvent {

    private final String id;
    private final String name;
    private final Integer heal;
    private final Integer initiative;


    public CharacterAdded( String id, String name, Integer heal, Integer initiative) {
        super(EventsEnum.CHARACTER_ADDED.name());
        this.id = id;
        this.name = name;
        this.heal = heal;
        this.initiative = initiative;
    }



    public String getName() {
        return name;
    }

    public Integer getHeal() {
        return heal;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public String getId() {
        return id;
    }
}
