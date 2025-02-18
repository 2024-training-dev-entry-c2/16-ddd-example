package com.buildingblocks.industries.domain.industry.events;

import com.buildingblocks.shared.domain.generic.DomainEvent;

public class UpgradedIndustry extends DomainEvent {
    private final String id;
    private final String type;
    private final Integer level;
    private final String location;
    private final Boolean isFlipped;
    private final String requiredResource;
    private final Integer quantityRequiredResource;
    private final Integer cost;
    private final Integer techLevelRequired;
    private final Boolean isRequiredResearch;
    private final String era;

    public UpgradedIndustry(String id, String type, Integer level, String location, Boolean isFlipped, String requiredResource, Integer quantityRequiredResource, Integer cost, Integer techLevelRequired, Boolean isRequiredResearch, String era) {
        super(EventsEnum.UPGRADED_INDUSTRY.name());
        this.id = id;
        this.type = type;
        this.level = level;
        this.location = location;
        this.isFlipped = isFlipped;
        this.requiredResource = requiredResource;
        this.quantityRequiredResource = quantityRequiredResource;
        this.cost = cost;
        this.techLevelRequired = techLevelRequired;
        this.isRequiredResearch = isRequiredResearch;
        this.era = era;
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

    public String getLocation() {
        return location;
    }

    public Boolean getFlipped() {
        return isFlipped;
    }

    public String getRequiredResource() {
        return requiredResource;
    }

    public Integer getQuantityRequiredResource() {
        return quantityRequiredResource;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getTechLevelRequired() {
        return techLevelRequired;
    }

    public Boolean getIsRequiredResearch() {
        return isRequiredResearch;
    }

    public String getEra() {
        return era;
    }
}
