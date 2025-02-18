package com.buildingblocks.industries.domain.industry;

import com.buildingblocks.industries.domain.industry.entities.MarketLink;
import com.buildingblocks.industries.domain.industry.entities.UpgradeStage;
import com.buildingblocks.industries.domain.industry.events.*;
import com.buildingblocks.industries.domain.industry.values.*;
import com.buildingblocks.shared.domain.generic.AggregateRoot;

public class Industry extends AggregateRoot<IndustryId> {
    private MarketLink marketLink;
    private UpgradeStage upgradeStage;
    private Cost cost;
    private Era era;
    private Income income;
    private IsConnectedToNetwork isConnectedToNetwork;
    private IsFlipped isFlipped;
    private IsRequiredResearch isRequiredResearch;
    private Level level;
    private Location location;
    private QuantityRequiredResource quantityRequiredResource;
    private RequiredResource requiredResource;
    private StoredResources storedResources;
    private Type type;
    private TechLevelRequired techLevelRequired;

    // region Constructors
    public Industry() {
        super(new IndustryId());
        subscribe(new IndustryHandler(this));
    }

    private Industry(IndustryId identity) {
        super(identity);
    }
    // endregion

    // region Getters and Setters
    public MarketLink getMarketLink() {
        return marketLink;
    }

    public void setMarketLink(MarketLink marketLink) {
        this.marketLink = marketLink;
    }

    public UpgradeStage getUpgradeStage() {
        return upgradeStage;
    }

    public void setUpgradeStage(UpgradeStage upgradeStage) {
        this.upgradeStage = upgradeStage;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public Era getEra() {
        return era;
    }

    public void setEra(Era era) {
        this.era = era;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public IsConnectedToNetwork getIsConnectedToNetwork() {
        return isConnectedToNetwork;
    }

    public void setIsConnectedToNetwork(IsConnectedToNetwork isConnectedToNetwork) {
        this.isConnectedToNetwork = isConnectedToNetwork;
    }

    public IsFlipped getIsFlipped() {
        return isFlipped;
    }

    public void setIsFlipped(IsFlipped isFlipped) {
        this.isFlipped = isFlipped;
    }

    public IsRequiredResearch getIsRequiredResearch() {
        return isRequiredResearch;
    }

    public void setIsRequiredResearch(IsRequiredResearch isRequiredResearch) {
        this.isRequiredResearch = isRequiredResearch;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public QuantityRequiredResource getQuantityRequiredResource() {
        return quantityRequiredResource;
    }

    public void setQuantityRequiredResource(QuantityRequiredResource quantityRequiredResource) {
        this.quantityRequiredResource = quantityRequiredResource;
    }

    public RequiredResource getRequiredResource() {
        return requiredResource;
    }

    public void setRequiredResource(RequiredResource requiredResource) {
        this.requiredResource = requiredResource;
    }

    public StoredResources getStoredResources() {
        return storedResources;
    }

    public void setStoredResources(StoredResources storedResources) {
        this.storedResources = storedResources;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public TechLevelRequired getTechLevelRequired() {
        return techLevelRequired;
    }

    public void setTechLevelRequired(TechLevelRequired techLevelRequired) {
        this.techLevelRequired = techLevelRequired;
    }

    // endregion

    // region Domain Actions
    public void activateMarketLink(String id, Boolean isConnectedToNetwork) {
        apply(new ActivatedIndustryMarketLink(id, isConnectedToNetwork));
    }

    public void build(String type, Integer level, String location, Integer cost, String requiredResource, Integer techLevelRequired, Boolean isConnectedToNetwork, String era, Boolean isFlipped) {
        apply(new BuiltIndustry(type, level, location, cost, requiredResource, techLevelRequired, isConnectedToNetwork, era, isFlipped));
    }

    public void consumeResource(String id, String requiredResource, Integer quantityRequiredResource) {
        apply(new ConsumedResource(id, requiredResource, quantityRequiredResource));
    }

    public void isExhausted(String id, String location, String type, Boolean isFlipped) {
        apply(new ExhaustedIndustry(id, location, type, isFlipped));
    }

    public void flip(String id, String location, String requiredResource, Integer quantityRequiredResource, Integer income) {
        apply(new FlippedIndustry(id, location, requiredResource, quantityRequiredResource, income));
    }

    public void overBuild(String id, String type, Integer level, Integer cost) {
        apply(new OverBuiltIndustry(id, type, level, cost) );
    }

    public void upgrade(String id, String type, Integer level, String location, Boolean isFlipped, String requiredResource, Integer quantityRequiredResource, Integer cost, Integer techLevelRequired, Boolean isRequiredResearch, String era) {
        apply(new UpgradedIndustry(id, type, level, location, isFlipped, requiredResource, quantityRequiredResource, cost, techLevelRequired, isRequiredResearch, era));
    }
    // endregion
}
