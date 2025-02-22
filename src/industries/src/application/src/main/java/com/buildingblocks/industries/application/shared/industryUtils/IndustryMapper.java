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
                                link.getOrigin().getValue(),
                                link.getDestination().getValue(),
                                link.getIsConnectedToNetwork().getValue()
                        )
                ).collect(Collectors.toList()),
                industry.getUpgradeStage().stream().map(stage ->
                        new IndustryResponse.UpgradeStage(
                                stage.getIdentity().getValue(),
                                stage.getCurrentLevel().getValue(),
                                stage.getRequiredTechLevel().getValue(),
                                stage.getType().getValue(),
                                stage.getRequiredResource().getValue(),
                                stage.getQuantityRequiredResource().getValue()
                        )
                ).collect(Collectors.toList()),
                industry.getCost().getValue(),
                industry.getEra().getValue(),
                industry.getIncome().getValue(),
                industry.getIsConnectedToNetwork().getValue(),
                industry.getIsFlipped().getValue(),
                industry.getIsRequiredResearch().getValue(),
                industry.getLevel().getValue(),
                industry.getLocation().getValue(),
                industry.getQuantityRequiredResource().getValue(),
                industry.getRequiredResource().getValue(),
                industry.getStoredResources().getValue(),
                industry.getType().getValue(),
                industry.getTechLevelRequired().getValue()
        );
    }
}
