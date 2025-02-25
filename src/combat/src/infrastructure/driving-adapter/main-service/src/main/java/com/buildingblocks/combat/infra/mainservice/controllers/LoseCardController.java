package com.buildingblocks.combat.infra.mainservice.controllers;

import com.buildingblocks.shared.application.deckOfCards.loseCard.LoseCardRequest;
import com.buildingblocks.shared.application.deckOfCards.loseCard.LoseCardUseCase;
import com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/lose-card")
public class LoseCardController {
    private final LoseCardUseCase loseCardUseCase;

    public LoseCardController(LoseCardUseCase loseCardUseCase) {
        this.loseCardUseCase = loseCardUseCase;
    }
    @PostMapping
    public Mono<DeckOfCardsResponse> loseCard( @RequestBody LoseCardRequest request){
            return loseCardUseCase.execute(request);
    }
}
