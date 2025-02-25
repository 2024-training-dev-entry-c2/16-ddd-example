package com.buildingblocks.industries.infra.mainservice.controllers;

import com.buildingblocks.industries.application.industry.upgradeindustry.UpgradeIndustryRequest;
import com.buildingblocks.industries.application.industry.upgradeindustry.UpgradeIndustryUseCase;
import com.buildingblocks.industries.application.shared.industryUtils.IndustryResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/upgrade-industry")
public class UpgradeIndustryController {
    private final UpgradeIndustryUseCase useCase;

    public UpgradeIndustryController(UpgradeIndustryUseCase useCase) {
        this.useCase = useCase;
    }

    @PutMapping
    public Mono<IndustryResponse> execute(@RequestBody UpgradeIndustryRequest request) {
        return useCase.execute(request);
    }
}
