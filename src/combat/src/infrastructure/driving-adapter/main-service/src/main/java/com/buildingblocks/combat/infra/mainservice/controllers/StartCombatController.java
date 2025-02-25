package com.buildingblocks.combat.infra.mainservice.controllers;

import com.buildingblocks.shared.application.combat.startCombat.StartCombatRequest;
import com.buildingblocks.shared.application.combat.startCombat.StartCombatUseCase;
import com.buildingblocks.shared.application.shared.combat.CombatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/start-combat")
public class StartCombatController {
    private final StartCombatUseCase startCombatUseCase;

    public StartCombatController(StartCombatUseCase startCombatUseCase) {
        this.startCombatUseCase = startCombatUseCase;
    }
    @PostMapping
    public Mono<CombatResponse> startCombat(@RequestBody StartCombatRequest request) {
        System.out.println("Received startCombat request: {}"+ request);
        return startCombatUseCase.execute(request)
                .doOnError(error -> System.out.println("Error processing request: {}"+ error.getMessage()));
    }
}
