package com.buildingblocks.industries.infra.mainservice.controllers.industryControllers;

import com.buildingblocks.industries.application.industry.flipindustry.FlipIndustryRequest;
import com.buildingblocks.industries.application.industry.flipindustry.FlipIndustryUseCase;
import com.buildingblocks.industries.application.shared.industryUtils.IndustryResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/flip-industry")
public class FlipIndustryController {
    private final FlipIndustryUseCase useCase;

    public FlipIndustryController(FlipIndustryUseCase useCase) {
        this.useCase = useCase;
    }

    @PutMapping
    public Mono<IndustryResponse> execute(@RequestBody FlipIndustryRequest request) {
        return useCase.execute(request);
    }
}
