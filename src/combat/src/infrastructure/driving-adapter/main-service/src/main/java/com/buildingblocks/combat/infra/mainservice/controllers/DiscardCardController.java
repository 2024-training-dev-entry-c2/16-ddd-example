package com.buildingblocks.combat.infra.mainservice.controllers;

import com.buildingblocks.shared.application.deckOfCards.discardCard.DiscardCardRequest;
import com.buildingblocks.shared.application.deckOfCards.discardCard.DiscardCardUseCase;
import com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/discard-card")
public class DiscardCardController {
    private final DiscardCardUseCase discardCardUseCase;

    public DiscardCardController(DiscardCardUseCase discardCardUseCase) {
        this.discardCardUseCase = discardCardUseCase;
    }
    @PostMapping
    public Mono<DeckOfCardsResponse> discardCard(@RequestBody DiscardCardRequest request){
        return discardCardUseCase.execute(request);
    }

}
