package com.buildingblocks.industries.infra.mainservice.controllers;

import com.buildingblocks.industries.application.industry.exhaustindustry.ExhaustIndustryRequest;
import com.buildingblocks.industries.application.industry.exhaustindustry.ExhaustIndustryUseCase;
import com.buildingblocks.industries.application.shared.industryUtils.IndustryResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/exhaust-industry")
public class ExhaustIndustryController {
    private final ExhaustIndustryUseCase useCase;

    public ExhaustIndustryController(ExhaustIndustryUseCase useCase) {
        this.useCase = useCase;
    }

    @PutMapping
    public Mono<IndustryResponse> execute(@RequestBody ExhaustIndustryRequest request) {
        return useCase.execute(request);
    }
}
