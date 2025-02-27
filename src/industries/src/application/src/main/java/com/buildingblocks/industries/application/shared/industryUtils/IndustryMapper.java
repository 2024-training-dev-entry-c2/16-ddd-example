package com.buildingblocks.industries.application.shared.industryUtils;

import com.buildingblocks.industries.domain.industry.Industry;
import com.buildingblocks.industries.domain.industry.values.Income;

import java.util.List;
import java.util.Optional;
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
                industry.getIncome() != null ? industry.getIncome().getValue() : 0,
                industry.getIsConnectedToNetwork().getValue(),
                industry.getIsFlipped() != null ? industry.getIsFlipped().getValue() : false,
                industry.getIsRequiredResearch() != null ? industry.getIsRequiredResearch().getValue() : false,
                industry.getLevel().getValue(),
                industry.getLocation().getValue(),
                industry.getQuantityRequiredResource() != null ? industry.getQuantityRequiredResource().getValue() : 0,
                industry.getRequiredResource().getValue(),
                industry.getStoredResources() != null
                        ? Optional.ofNullable(industry.getStoredResources().getValue()).orElse(List.of())
                        : List.of(),
                industry.getType().getValue(),
                industry.getTechLevelRequired().getValue()
        );
    }
}
