package com.buildingblocks.industries.infra.mainservice.controllers;

import com.buildingblocks.industries.application.industry.consumeresource.ConsumeResourceRequest;
import com.buildingblocks.industries.application.industry.consumeresource.ConsumeResourceUseCase;
import com.buildingblocks.industries.application.shared.industryUtils.IndustryResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/consume-resource")
public class ConsumeResourceController {
    private final ConsumeResourceUseCase useCase;

    public ConsumeResourceController(ConsumeResourceUseCase useCase) {
        this.useCase = useCase;
    }

    @PutMapping
    public Mono<IndustryResponse> execute(@RequestBody ConsumeResourceRequest request) {
        return useCase.execute(request);
    }
}
