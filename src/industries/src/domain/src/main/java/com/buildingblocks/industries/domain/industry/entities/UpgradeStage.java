package com.buildingblocks.industries.domain.industry.entities;

import com.buildingblocks.industries.domain.industry.values.*;
import com.buildingblocks.shared.domain.generic.Entity;
import com.buildingblocks.shared.domain.utils.ValueValidator;

public class UpgradeStage extends Entity<UpgradeStageId> {
    private Level currentLevel;
    private TechLevelRequired requiredTechLevel;
    private Type type;
    private RequiredResource requiredResource;
    private QuantityRequiredResource quantityRequiredResource;

    // region Constructors
    public UpgradeStage(Level currentLevel, TechLevelRequired requiredTechLevel, Type type, RequiredResource requiredResource, QuantityRequiredResource quantityRequiredResource) {
        super(new UpgradeStageId());
        this.currentLevel = currentLevel;
        this.requiredTechLevel = requiredTechLevel;
        this.type = type;
        this.requiredResource = requiredResource;
        this.quantityRequiredResource = quantityRequiredResource;
    }

    public UpgradeStage(UpgradeStageId identity, Level currentLevel, TechLevelRequired requiredTechLevel, Type type, RequiredResource requiredResource, QuantityRequiredResource quantityRequiredResource) {
        super(identity);
        this.currentLevel = currentLevel;
        this.requiredTechLevel = requiredTechLevel;
        this.type = type;
        this.requiredResource = requiredResource;
        this.quantityRequiredResource = quantityRequiredResource;
    }
    // endregion

    // region Public Methods
    public void upgradeIndustry(int availableResource) {
        if (currentLevel.getValue() >= requiredTechLevel.getValue())
            throw new IllegalStateException("Industry is already at or above the required tech level.");

        if (availableResource < quantityRequiredResource.getValue())
            throw new IllegalStateException("Not enough resources to upgrade.");

        this.currentLevel = Level.of(currentLevel.getValue() + 1);
    }
    // endregion

    // region Getters and Setters
    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public TechLevelRequired getRequiredTechLevel() {
        return requiredTechLevel;
    }

    public void setRequiredTechLevel(TechLevelRequired requiredTechLevel) {
        ValueValidator.validateNotNull(requiredTechLevel, "TechLevelRequired");
        this.requiredTechLevel = requiredTechLevel;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        ValueValidator.validateNotNull(type, "Type");
        this.type = type;
    }

    public RequiredResource getRequiredResource() {
        return requiredResource;
    }

    public void setRequiredResource(RequiredResource requiredResource) {
        ValueValidator.validateNotNull(requiredResource, "RequiredResource");
        this.requiredResource = requiredResource;
    }

    public QuantityRequiredResource getQuantityRequiredResource() {
        return quantityRequiredResource;
    }

    public void setQuantityRequiredResource(QuantityRequiredResource quantityRequiredResource) {
        this.quantityRequiredResource = quantityRequiredResource;
    }
    // endregion
}
