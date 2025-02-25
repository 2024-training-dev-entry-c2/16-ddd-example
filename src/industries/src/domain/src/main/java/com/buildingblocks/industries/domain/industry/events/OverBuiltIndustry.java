package com.buildingblocks.industries.domain.industry.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class OverBuiltIndustry extends DomainEvent {
    private String id;
    private String type;
    private Integer level;
    private Integer cost;

    public OverBuiltIndustry() {
        super(EventsEnum.OVERBUILT_INDUSTRY.name());
    }

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

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
