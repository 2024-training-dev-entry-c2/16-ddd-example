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
                industry.getIncome().getValue(),
                industry.getIsConnectedToNetwork().getValue(),
                industry.getIsFlipped().getValue(),
                industry.getIsRequiredResearch().getValue(),
                industry.getLevel().getValue(),
                industry.getLocation().getValue(),
                industry.getQuantityRequiredResource().getValue(),
                industry.getRequiredResource().getValue(),
                Optional.ofNullable(industry.getStoredResources())
                        .map(resources -> resources.getValue().stream()
                                .map(Object::toString)
                                .collect(Collectors.toList()))
                        .orElse(List.of()),

                industry.getType().getValue(),
                industry.getTechLevelRequired().getValue()
        );
    }
}
