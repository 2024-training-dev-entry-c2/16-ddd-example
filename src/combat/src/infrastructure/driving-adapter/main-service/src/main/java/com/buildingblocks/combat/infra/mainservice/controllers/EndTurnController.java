package com.buildingblocks.combat.infra.mainservice.controllers;

import com.buildingblocks.shared.application.combat.endTurn.EndTurnRequest;
import com.buildingblocks.shared.application.combat.endTurn.EndTurnUseCase;
import com.buildingblocks.shared.application.shared.combat.CombatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/end-turn")
public class EndTurnController {
private final EndTurnUseCase endTurnUseCase;

    public EndTurnController(EndTurnUseCase endTurnUseCase) {
        this.endTurnUseCase = endTurnUseCase;
    }
    @PostMapping
    public Mono<CombatResponse> endTurn(@RequestBody EndTurnRequest request) {
        return endTurnUseCase.execute(request);
    }
}
