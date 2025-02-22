package com.buildingblocks.industries.application.shared.industryUtils;

import java.util.List;

public class IndustryResponse {
    private final String industryId;
    private final List<MarketLink> marketLink;
    private final List<UpgradeStage> upgradeStage;
    private final Integer cost;
    private final String era;
    private final Integer income;
    private final Boolean isConnectedToNetwork;
    private final Boolean isFlipped;
    private final Boolean isRequiredResearch;
    private final Integer level;
    private final String location;
    private final Integer quantityRequiredResource;
    private final String requiredResource;
    private final List<String> storedResources;
    private final String type;
    private final Integer techLevelRequired;

    public IndustryResponse(String industryId, List<MarketLink> marketLink, List<UpgradeStage> upgradeStage, Integer cost, String era, Integer income, Boolean isConnectedToNetwork, Boolean isFlipped, Boolean isRequiredResearch, Integer level, String location, Integer quantityRequiredResource, String requiredResource, List<String> storedResources, String type, Integer techLevelRequired) {
        this.industryId = industryId;
        this.marketLink = marketLink;
        this.upgradeStage = upgradeStage;
        this.cost = cost;
        this.era = era;
        this.income = income;
        this.isConnectedToNetwork = isConnectedToNetwork;
        this.isFlipped = isFlipped;
        this.isRequiredResearch = isRequiredResearch;
        this.level = level;
        this.location = location;
        this.quantityRequiredResource = quantityRequiredResource;
        this.requiredResource = requiredResource;
        this.storedResources = storedResources;
        this.type = type;
        this.techLevelRequired = techLevelRequired;
    }

    public String getIndustryId() {
        return industryId;
    }

    public List<MarketLink> getMarketLink() {
        return marketLink;
    }

    public List<UpgradeStage> getUpgradeStage() {
        return upgradeStage;
    }

    public Integer getCost() {
        return cost;
    }

    public String getEra() {
        return era;
    }

    public Integer getIncome() {
        return income;
    }

    public Boolean getConnectedToNetwork() {
        return isConnectedToNetwork;
    }

    public Boolean getFlipped() {
        return isFlipped;
    }

    public Boolean getRequiredResearch() {
        return isRequiredResearch;
    }

    public Integer getLevel() {
        return level;
    }

    public String getLocation() {
        return location;
    }

    public Integer getQuantityRequiredResource() {
        return quantityRequiredResource;
    }

    public String getRequiredResource() {
        return requiredResource;
    }

    public List<String> getStoredResources() {
        return storedResources;
    }

    public String getType() {
        return type;
    }

    public Integer getTechLevelRequired() {
        return techLevelRequired;
    }

    public static class MarketLink {
        private final String id;
        private final String origin;
        private final String destination;
        private final Boolean isConnectedToNetwork;

        public MarketLink(String id, String origin, String destination, Boolean isConnectedToNetwork) {
            this.id = id;
            this.origin = origin;
            this.destination = destination;
            this.isConnectedToNetwork = isConnectedToNetwork;
        }

        public String getId() {
            return id;
        }

        public String getOrigin() {
            return origin;
        }

        public String getDestination() {
            return destination;
        }

        public Boolean getConnectedToNetwork() {
            return isConnectedToNetwork;
        }
    }

    public static class UpgradeStage {
        private final String id;
        private final Integer currentLevel;
        private final Integer requiredTechLevel;
        private final String type;
        private final String requiredResource;
        private final Integer quantityRequiredResource;

        public UpgradeStage(String id, Integer currentLevel, Integer requiredTechLevel, String type, String requiredResource, Integer quantityRequiredResource) {
            this.id = id;
            this.currentLevel = currentLevel;
            this.requiredTechLevel = requiredTechLevel;
            this.type = type;
            this.requiredResource = requiredResource;
            this.quantityRequiredResource = quantityRequiredResource;
        }

        public String getId() {
            return id;
        }

        public Integer getCurrentLevel() {
            return currentLevel;
        }

        public Integer getRequiredTechLevel() {
            return requiredTechLevel;
        }

        public String getType() {
            return type;
        }

        public String getRequiredResource() {
            return requiredResource;
        }

        public Integer getQuantityRequiredResource() {
            return quantityRequiredResource;
        }
    }

}