package com.buildingblocks.shared.application.combat.addCharacter;
import com.buildingblocks.shared.application.Request;

public class AddCharacterRequest extends Request {
    private final String id;
    private final String name;
    private final Integer heal;
    private final Integer initiative;

    public AddCharacterRequest( String aggregateId,String id, String name, Integer heal, Integer initiative) {
        super(aggregateId);
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
