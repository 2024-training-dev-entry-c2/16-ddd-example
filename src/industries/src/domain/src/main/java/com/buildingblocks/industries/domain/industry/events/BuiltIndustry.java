package com.buildingblocks.industries.domain.industry.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class BuiltIndustry extends DomainEvent {
    private final String type;
    private final Integer level;
    private final String location;
    private final Integer cost;
    private final String requiredResource;
    private final Integer techLevelRequired;
    private final Boolean isConnectedToNetwork;
    private final String era;
    private final Boolean isFlipped;

    public BuiltIndustry(String type, Integer level, String location, Integer cost, String requiredResource, Integer techLevelRequired, Boolean isConnectedToNetwork, String era, Boolean isFlipped) {
        super(EventsEnum.BUILT_INDUSTRY.name());
        this.type = type;
        this.level = level;
        this.location = location;
        this.cost = cost;
        this.requiredResource = requiredResource;
        this.techLevelRequired = techLevelRequired;
        this.isConnectedToNetwork = isConnectedToNetwork;
        this.era = era;
        this.isFlipped = isFlipped;
    }

    public String getType() {
        return type;
    }

    public Integer getLevel() {
        return level;
    }

    public String getLocation() {
        return location;
    }

    public Integer getCost() {
        return cost;
    }

    public String getRequiredResource() {
        return requiredResource;
    }

    public Integer getTechLevelRequired() {
        return techLevelRequired;
    }

    public Boolean getIsConnectedToNetwork() {
        return isConnectedToNetwork;
    }

    public String getEra() {
        return era;
    }

    public Boolean getFlipped() {
        return isFlipped;
    }
}
