package com.buildingblocks.combat.infra.mainservice.controllers;

import com.buildingblocks.shared.application.combat.addCharacter.AddCharacterRequest;
import com.buildingblocks.shared.application.combat.addCharacter.AddCharacterUseCase;
import com.buildingblocks.shared.application.shared.combat.CombatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/add-character")
public class AddCharacterController {
    private final AddCharacterUseCase addCharacterUseCase;

    public AddCharacterController(AddCharacterUseCase addCharacterUseCase) {
        this.addCharacterUseCase = addCharacterUseCase;
    }
    @PostMapping
    public Mono<CombatResponse> addCharacter(@RequestBody AddCharacterRequest request){
        return addCharacterUseCase.execute(request);
    }

}
