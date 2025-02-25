package com.buildingblocks.shared.application.combat.domain.combat.events;

import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

public class CharacterAdded extends DomainEvent {

    private  String id;
    private  String name;
    private  Integer heal;
    private  Integer initiative;

   public CharacterAdded() {
        super(EventsEnum.CHARACTER_ADDED.name());
    }
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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeal(Integer heal) {
        this.heal = heal;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }
}
