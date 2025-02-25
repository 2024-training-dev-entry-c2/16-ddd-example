package com.buildingblocks.combat.infra.mainservice.controllers;

import com.buildingblocks.shared.application.combat.removeCharacter.RemoveCharacterRequest;
import com.buildingblocks.shared.application.combat.removeCharacter.RemoveCharacterUseCase;
import com.buildingblocks.shared.application.shared.combat.CombatResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/api/remove-character")
public class RemoveCharacterController {
    private final RemoveCharacterUseCase removeCharacterUseCase;

    public RemoveCharacterController(RemoveCharacterUseCase removeCharacterUseCase) {
        this.removeCharacterUseCase = removeCharacterUseCase;
    }
    @DeleteMapping
    public Mono<CombatResponse> removeCharacter(@RequestBody RemoveCharacterRequest request){
        return removeCharacterUseCase.execute(request);
    }
}
