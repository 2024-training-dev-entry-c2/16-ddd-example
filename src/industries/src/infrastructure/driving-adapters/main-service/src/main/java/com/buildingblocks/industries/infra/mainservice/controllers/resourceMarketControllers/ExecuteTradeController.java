package com.buildingblocks.industries.infra.mainservice.controllers.resourceMarketControllers;

import com.buildingblocks.industries.application.resourceMarket.executetrade.ExecuteTradeRequest;
import com.buildingblocks.industries.application.resourceMarket.executetrade.ExecuteTradeUseCase;
import com.buildingblocks.industries.application.shared.ResourceMarketUtils.ResourceMarketResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/resource-market-trade")
public class ExecuteTradeController {
    private final ExecuteTradeUseCase useCase;

    public ExecuteTradeController(ExecuteTradeUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public Mono<ResourceMarketResponse> execute(@RequestBody ExecuteTradeRequest request) {
        return useCase.execute(request);
    }
}
