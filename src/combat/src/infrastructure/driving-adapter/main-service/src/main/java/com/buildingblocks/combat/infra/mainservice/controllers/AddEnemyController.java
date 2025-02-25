package com.buildingblocks.combat.infra.mainservice.controllers;

import com.buildingblocks.shared.application.combat.addEnemy.AddEnemyRequest;
import com.buildingblocks.shared.application.combat.addEnemy.AddEnemyUseCase;
import com.buildingblocks.shared.application.shared.combat.CombatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/add-enemy")
public class AddEnemyController {
    private final AddEnemyUseCase addEnemyUseCase;

    public AddEnemyController(AddEnemyUseCase addEnemyUseCase) {
        this.addEnemyUseCase = addEnemyUseCase;
    }

    @PostMapping
    public Mono<CombatResponse> addEnemy(@RequestBody AddEnemyRequest request) {
        return addEnemyUseCase.execute(request);
    }
}
