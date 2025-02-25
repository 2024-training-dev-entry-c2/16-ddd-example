package com.buildingblocks.industries.infra.mainservice.controllers.resourceMarketControllers;

import com.buildingblocks.industries.application.resourceMarket.depletemarket.DepleteMarketRequest;
import com.buildingblocks.industries.application.resourceMarket.depletemarket.DepleteMarketUseCase;
import com.buildingblocks.industries.application.shared.ResourceMarketUtils.ResourceMarketResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/resource-market-deplete")
public class DepleteMarketController {
    private final DepleteMarketUseCase useCase;

    public DepleteMarketController(DepleteMarketUseCase useCase) {
        this.useCase = useCase;
    }

    @PutMapping
    public Mono<ResourceMarketResponse> execute(@RequestBody DepleteMarketRequest request) {
        return useCase.execute(request);
    }
}

