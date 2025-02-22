package com.buildingblocks.industries.application.shared.industryUtils;

import com.buildingblocks.industries.domain.industry.values.*;

import java.util.List;

public class IndustryResponse {
    private final String industryId;
    private final List<MarketLink> marketLink;
    private final List<UpgradeStage> upgradeStage;
    private final Cost cost;
    private final Era era;
    private final Income income;
    private final IsConnectedToNetwork isConnectedToNetwork;
    private final IsFlipped isFlipped;
    private final IsRequiredResearch isRequiredResearch;
    private final Level level;
    private final Location location;
    private final QuantityRequiredResource quantityRequiredResource;
    private final RequiredResource requiredResource;
    private final StoredResources storedResources;
    private final Type type;
    private final TechLevelRequired techLevelRequired;

    public IndustryResponse(String industryId, List<MarketLink> marketLink, List<UpgradeStage> upgradeStage, Cost cost, Era era, Income income, IsConnectedToNetwork isConnectedToNetwork, IsFlipped isFlipped, IsRequiredResearch isRequiredResearch, Level level, Location location, QuantityRequiredResource quantityRequiredResource, RequiredResource requiredResource, StoredResources storedResources, Type type, TechLevelRequired techLevelRequired) {
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

    public Cost getCost() {
        return cost;
    }

    public Era getEra() {
        return era;
    }

    public Income getIncome() {
        return income;
    }

    public IsConnectedToNetwork getIsConnectedToNetwork() {
        return isConnectedToNetwork;
    }

    public IsFlipped getIsFlipped() {
        return isFlipped;
    }

    public IsRequiredResearch getIsRequiredResearch() {
        return isRequiredResearch;
    }

    public Level getLevel() {
        return level;
    }

    public Location getLocation() {
        return location;
    }

    public QuantityRequiredResource getQuantityRequiredResource() {
        return quantityRequiredResource;
    }

    public RequiredResource getRequiredResource() {
        return requiredResource;
    }

    public StoredResources getStoredResources() {
        return storedResources;
    }

    public Type getType() {
        return type;
    }

    public TechLevelRequired getTechLevelRequired() {
        return techLevelRequired;
    }

    public static class MarketLink {
        private final String id;
        private final Origin origin;
        private final Destination destination;
        private final IsConnectedToNetwork isConnectedToNetwork;

        public MarketLink(String id, Origin origin, Destination destination, IsConnectedToNetwork isConnectedToNetwork) {
            this.id = id;
            this.origin = origin;
            this.destination = destination;
            this.isConnectedToNetwork = isConnectedToNetwork;
        }

        public String getId() {
            return id;
        }

        public Origin getOrigin() {
            return origin;
        }

        public Destination getDestination() {
            return destination;
        }

        public IsConnectedToNetwork getIsConnectedToNetwork() {
            return isConnectedToNetwork;
        }
    }

    public static class UpgradeStage {
        private final String id;
        private final Level currentLevel;
        private final TechLevelRequired requiredTechLevel;
        private final Type type;
        private final RequiredResource requiredResource;
        private final QuantityRequiredResource quantityRequiredResource;

        public UpgradeStage(String id, Level currentLevel, TechLevelRequired requiredTechLevel, Type type, RequiredResource requiredResource, QuantityRequiredResource quantityRequiredResource) {
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

        public Level getCurrentLevel() {
            return currentLevel;
        }

        public TechLevelRequired getRequiredTechLevel() {
            return requiredTechLevel;
        }

        public Type getType() {
            return type;
        }

        public RequiredResource getRequiredResource() {
            return requiredResource;
        }

        public QuantityRequiredResource getQuantityRequiredResource() {
            return quantityRequiredResource;
        }
    }

}