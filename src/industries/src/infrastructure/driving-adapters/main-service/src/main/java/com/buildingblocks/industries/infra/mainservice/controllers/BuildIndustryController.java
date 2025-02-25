package com.buildingblocks.industries.infra.mainservice.controllers;

import com.buildingblocks.industries.application.industry.buildindustry.BuildIndustryRequest;
import com.buildingblocks.industries.application.industry.buildindustry.BuildIndustryUseCase;
import com.buildingblocks.industries.application.shared.industryUtils.IndustryResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/build-industry")
public class BuildIndustryController {
    private final BuildIndustryUseCase useCase;

    public BuildIndustryController(BuildIndustryUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public Mono<IndustryResponse> execute(@RequestBody BuildIndustryRequest request) {
        return useCase.execute(request);
    }
}
