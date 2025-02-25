package com.buildingblocks.combat.infra.mainservice.controllers;

import com.buildingblocks.shared.application.combat.endCombat.EndCombatRequest;
import com.buildingblocks.shared.application.combat.endCombat.EndCombatUseCase;
import com.buildingblocks.shared.application.shared.combat.CombatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/end-combat")
public class EndCombatController {
    private final EndCombatUseCase endCombatUseCase;

    public EndCombatController(EndCombatUseCase endCombatUseCase) {
        this.endCombatUseCase = endCombatUseCase;
    }
    @PostMapping
    public Mono<CombatResponse> endCombat(@RequestBody EndCombatRequest request) {
        return endCombatUseCase.execute(request);
    }
}
