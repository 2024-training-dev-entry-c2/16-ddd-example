package com.buildingblocks.industries.infra.mainservice.controllers.resourceMarketControllers;

import com.buildingblocks.industries.application.resourceMarket.addmarket.AddMarketRequest;
import com.buildingblocks.industries.application.resourceMarket.addmarket.AddMarketUseCase;
import com.buildingblocks.industries.application.shared.ResourceMarketUtils.ResourceMarketResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/add-market")
public class AddMarketController {
    private final AddMarketUseCase useCase;

    public AddMarketController(AddMarketUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public Mono<ResourceMarketResponse> execute(@RequestBody AddMarketRequest request) {
        return useCase.execute(request);
    }
}
