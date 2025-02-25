package com.buildingblocks.combat.infra.mainservice.controllers;

import com.buildingblocks.shared.application.combat.removeEnemy.RemoveEnemyRequest;
import com.buildingblocks.shared.application.combat.removeEnemy.RemoveEnemyUseCase;
import com.buildingblocks.shared.application.shared.combat.CombatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/remove-enemy")
public class RemoveEnemyController {
    private final RemoveEnemyUseCase removeEnemyUseCase;
    public RemoveEnemyController(RemoveEnemyUseCase removeEnemyUseCase) {
        this.removeEnemyUseCase = removeEnemyUseCase;
    }
    @PostMapping
    public Mono<CombatResponse> removeEnemy(@RequestBody RemoveEnemyRequest request){
        return removeEnemyUseCase.execute(request);
    }
}
