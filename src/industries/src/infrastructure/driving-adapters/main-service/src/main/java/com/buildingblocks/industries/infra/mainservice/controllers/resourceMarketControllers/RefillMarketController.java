package com.buildingblocks.industries.infra.mainservice.controllers.resourceMarketControllers;

import com.buildingblocks.industries.application.resourceMarket.refillmarket.RefillMarketRequest;
import com.buildingblocks.industries.application.resourceMarket.refillmarket.RefillMarketUseCase;
import com.buildingblocks.industries.application.shared.ResourceMarketUtils.ResourceMarketResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/resource-market/refill")
public class RefillMarketController {
    private final RefillMarketUseCase useCase;

    public RefillMarketController(RefillMarketUseCase useCase) {
        this.useCase = useCase;
    }

    @PutMapping
    public Mono<ResourceMarketResponse> execute(@RequestBody RefillMarketRequest request) {
        return useCase.execute(request);
    }
}
