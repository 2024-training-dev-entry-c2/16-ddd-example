package com.buildingblocks.combat.infra.mainservice.controllers;

import com.buildingblocks.shared.application.deckOfCards.recoverCard.RecoverCardRequest;
import com.buildingblocks.shared.application.deckOfCards.recoverCard.RecoverCardUseCase;
import com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/recover-card")
public class RecoverCardController {
    private final RecoverCardUseCase recoverCardUseCase;

    public RecoverCardController(RecoverCardUseCase recoverCardUseCase) {
        this.recoverCardUseCase = recoverCardUseCase;
    }
    @PostMapping
    public Mono<DeckOfCardsResponse> recoverCard(@RequestBody RecoverCardRequest request){
        return recoverCardUseCase.execute(request);
    }

}
