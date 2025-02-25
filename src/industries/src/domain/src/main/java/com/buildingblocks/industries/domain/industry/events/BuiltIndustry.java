package com.buildingblocks.industries.domain.industry.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class BuiltIndustry extends DomainEvent {
    private String type;
    private Integer level;
    private String location;
    private Integer cost;
    private String requiredResource;
    private Integer techLevelRequired;
    private Boolean isConnectedToNetwork;
    private String era;
    private Boolean isFlipped;

    public BuiltIndustry() {
        super(EventsEnum.BUILT_INDUSTRY.name());
    }

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

    public void setType(String type) {
        this.type = type;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setRequiredResource(String requiredResource) {
        this.requiredResource = requiredResource;
    }

    public void setTechLevelRequired(Integer techLevelRequired) {
        this.techLevelRequired = techLevelRequired;
    }

    public void setConnectedToNetwork(Boolean connectedToNetwork) {
        isConnectedToNetwork = connectedToNetwork;
    }

    public void setEra(String era) {
        this.era = era;
    }

    public void setFlipped(Boolean flipped) {
        isFlipped = flipped;
    }
}
