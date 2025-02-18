package com.buildingblocks.industries.domain.industry.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class OverBuiltIndustry extends DomainEvent {
    private final String id;
    private final String type;
    private final Integer level;
    private final Integer cost;

    public OverBuiltIndustry(String id, String type, Integer level, Integer cost) {
        super(EventsEnum.OVERBUILT_INDUSTRY.name());
        this.id = id;
        this.type = type;
        this.level = level;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getCost() {
        return cost;
    }
}
