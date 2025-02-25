package com.buildingblocks.industries.infra.mainservice.controllers.playerControllers;

import com.buildingblocks.industries.application.player.takeloan.TakeLoanRequest;
import com.buildingblocks.industries.application.player.takeloan.TakeLoanUseCase;
import com.buildingblocks.industries.application.shared.playerUtils.PlayerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/player-loan")
public class TakeLoanController {
    private final TakeLoanUseCase useCase;

    public TakeLoanController(TakeLoanUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public Mono<PlayerResponse> execute(@RequestBody TakeLoanRequest request) {
        return useCase.execute(request);
    }
}
