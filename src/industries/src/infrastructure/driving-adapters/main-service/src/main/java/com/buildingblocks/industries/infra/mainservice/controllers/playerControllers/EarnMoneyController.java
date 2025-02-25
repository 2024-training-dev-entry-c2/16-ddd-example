package com.buildingblocks.industries.infra.mainservice.controllers.playerControllers;

import com.buildingblocks.industries.application.player.earnmoney.EarnMoneyRequest;
import com.buildingblocks.industries.application.player.earnmoney.EarnMoneyUseCase;
import com.buildingblocks.industries.application.shared.playerUtils.PlayerResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/player-earn-money")
public class EarnMoneyController {
    private final EarnMoneyUseCase useCase;

    public EarnMoneyController(EarnMoneyUseCase useCase) {
        this.useCase = useCase;
    }

    @PutMapping
    public Mono<PlayerResponse> execute(@RequestBody EarnMoneyRequest request) {
        return useCase.execute(request);
    }
}

