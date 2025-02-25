package com.buildingblocks.industries.infra.mainservice.controllers.playerControllers;

import com.buildingblocks.industries.application.player.makepurchase.MakePurchaseRequest;
import com.buildingblocks.industries.application.player.makepurchase.MakePurchaseUseCase;
import com.buildingblocks.industries.application.shared.playerUtils.PlayerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/player-purchase")
public class MakePurchaseController {
    private final MakePurchaseUseCase useCase;

    public MakePurchaseController(MakePurchaseUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public Mono<PlayerResponse> execute(@RequestBody MakePurchaseRequest request) {
        return useCase.execute(request);
    }
}

