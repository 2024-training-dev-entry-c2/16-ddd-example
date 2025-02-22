package com.buildingblocks.industries.application.industry.buildindustry;

import com.buildingblocks.shared.application.Request;

public class BuildIndustryRequest extends Request {
    private final String type;
    private final Integer level;
    private final String location;
    private final Integer cost;
    private final String requiredResource;
    private final Integer techLevelRequired;
    private final Boolean isConnectedToNetwork;
    private final String era;
    private final Boolean isFlipped;

    public BuildIndustryRequest(String type, Integer level, String location, Integer cost, String requiredResource, Integer techLevelRequired, Boolean isConnectedToNetwork, String era, Boolean isFlipped) {
        super(null);
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

    public Boolean getConnectedToNetwork() {
        return isConnectedToNetwork;
    }

    public String getEra() {
        return era;
    }

    public Boolean getFlipped() {
        return isFlipped;
    }
}
