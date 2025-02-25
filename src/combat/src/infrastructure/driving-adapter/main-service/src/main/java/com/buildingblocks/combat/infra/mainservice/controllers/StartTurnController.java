package com.buildingblocks.combat.infra.mainservice.controllers;

import com.buildingblocks.shared.application.combat.startTurn.StartTurnRequest;
import com.buildingblocks.shared.application.combat.startTurn.StartTurnUseCase;
import com.buildingblocks.shared.application.shared.combat.CombatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/start-turn")
public class StartTurnController {
    private final StartTurnUseCase startTurnUseCase;

    public StartTurnController(StartTurnUseCase startTurnUseCase) {
        this.startTurnUseCase = startTurnUseCase;
    }
    @PostMapping
    public Mono<CombatResponse> startTurn(@RequestBody StartTurnRequest request){
        return startTurnUseCase.execute(request);
    }
}
