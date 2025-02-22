package com.buildingblocks.industries.application.shared.industryUtils;

import com.buildingblocks.industries.domain.industry.Industry;

import java.util.stream.Collectors;

public class IndustryMapper {

    public static IndustryResponse mapToIndustry(Industry industry) {
        return new IndustryResponse(
                industry.getIdentity().getValue(),
                industry.getMarketLink().stream().map(link ->
                        new IndustryResponse.MarketLink(
                                link.getIdentity().getValue(),
                                link.getOrigin(),
                                link.getDestination(),
                                link.getIsConnectedToNetwork()
                        )
                ).collect(Collectors.toList()),
                industry.getUpgradeStage().stream().map(stage ->
                        new IndustryResponse.UpgradeStage(
                                stage.getIdentity().getValue(),
                                stage.getCurrentLevel(),
                                stage.getRequiredTechLevel(),
                                stage.getType(),
                                stage.getRequiredResource(),
                                stage.getQuantityRequiredResource()
                        )
                ).collect(Collectors.toList()),
                industry.getCost(),
                industry.getEra(),
                industry.getIncome(),
                industry.getIsConnectedToNetwork(),
                industry.getIsFlipped(),
                industry.getIsRequiredResearch(),
                industry.getLevel(),
                industry.getLocation(),
                industry.getQuantityRequiredResource(),
                industry.getRequiredResource(),
                industry.getStoredResources(),
                industry.getType(),
                industry.getTechLevelRequired()
        );
    }
}
