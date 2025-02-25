package com.buildingblocks.industries.infra.mainservice.controllers.playerControllers;

import com.buildingblocks.industries.application.player.adjustincome.AdjustIncomeRequest;
import com.buildingblocks.industries.application.player.adjustincome.AdjustIncomeUseCase;
import com.buildingblocks.industries.application.shared.playerUtils.PlayerResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/player-income")
public class AdjustIncomeController {
    private final AdjustIncomeUseCase useCase;

    public AdjustIncomeController(AdjustIncomeUseCase useCase) {
        this.useCase = useCase;
    }

    @PutMapping
    public Mono<PlayerResponse> execute(@RequestBody AdjustIncomeRequest request) {
        return useCase.execute(request);
    }
}
