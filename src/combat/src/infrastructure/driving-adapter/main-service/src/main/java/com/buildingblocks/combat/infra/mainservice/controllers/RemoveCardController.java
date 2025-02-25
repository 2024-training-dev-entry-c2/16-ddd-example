package com.buildingblocks.combat.infra.mainservice.controllers;

import com.buildingblocks.shared.application.deckOfCards.removeCard.RemoveCardRequest;
import com.buildingblocks.shared.application.deckOfCards.removeCard.RemoveCardUseCase;
import com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/remove-card")
public class RemoveCardController {
    private final RemoveCardUseCase removeCardUseCase;

    public RemoveCardController(RemoveCardUseCase removeCardUseCase) {
        this.removeCardUseCase = removeCardUseCase;
    }

    @DeleteMapping
    public Mono<DeckOfCardsResponse> removeCard(@RequestBody RemoveCardRequest request) {
        return removeCardUseCase.execute(request);
    }

}
