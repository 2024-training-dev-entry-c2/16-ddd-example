package com.buildingblocks.industries.infra.mainservice.controllers.playerControllers;

import com.buildingblocks.industries.application.player.addplayer.AddPlayerRequest;
import com.buildingblocks.industries.application.player.addplayer.AddPlayerUseCase;
import com.buildingblocks.industries.application.shared.playerUtils.PlayerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/add-player")
public class AddPlayerController {
    private final AddPlayerUseCase useCase;

    public AddPlayerController(AddPlayerUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public Mono<PlayerResponse> execute(@RequestBody AddPlayerRequest request) {
        return useCase.execute(request);
    }
}
