package com.buildingblocks.industries.infra.mainservice.controllers.industryControllers;

import com.buildingblocks.industries.application.industry.overbuildindustry.OverBuildIndustryRequest;
import com.buildingblocks.industries.application.industry.overbuildindustry.OverBuildIndustryUseCase;
import com.buildingblocks.industries.application.shared.industryUtils.IndustryResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/overbuild-industry")
public class OverBuildIndustryController {
    private final OverBuildIndustryUseCase useCase;

    public OverBuildIndustryController(OverBuildIndustryUseCase useCase) {
        this.useCase = useCase;
    }

    @PutMapping
    public Mono<IndustryResponse> execute(@RequestBody OverBuildIndustryRequest request) {
        return useCase.execute(request);
    }
}
