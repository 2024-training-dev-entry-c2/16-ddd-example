package com.buildingblocks.industries.application.industry.upgradeindustry;

import com.buildingblocks.shared.application.Request;

public class UpgradeIndustryRequest extends Request {
    private String id;
    private String type;
    private Integer level;
    private String location;
    private Boolean isFlipped;
    private String requiredResource;
    private Integer quantityRequiredResource;
    private Integer cost;
    private Integer techLevelRequired;
    private Boolean isRequiredResearch;
    private String era;

    public UpgradeIndustryRequest() {
        super(null);
    }

    public UpgradeIndustryRequest(String aggregateId, String id, String type, Integer level, String location, Boolean isFlipped, String requiredResource, Integer quantityRequiredResource, Integer cost, Integer techLevelRequired, Boolean isRequiredResearch, String era) {
        super(aggregateId);
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

    public Boolean getRequiredResearch() {
        return isRequiredResearch;
    }

    public String getEra() {
        return era;
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

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFlipped(Boolean flipped) {
        isFlipped = flipped;
    }

    public void setRequiredResource(String requiredResource) {
        this.requiredResource = requiredResource;
    }

    public void setQuantityRequiredResource(Integer quantityRequiredResource) {
        this.quantityRequiredResource = quantityRequiredResource;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setTechLevelRequired(Integer techLevelRequired) {
        this.techLevelRequired = techLevelRequired;
    }

    public void setRequiredResearch(Boolean requiredResearch) {
        isRequiredResearch = requiredResearch;
    }

    public void setEra(String era) {
        this.era = era;
    }
}
