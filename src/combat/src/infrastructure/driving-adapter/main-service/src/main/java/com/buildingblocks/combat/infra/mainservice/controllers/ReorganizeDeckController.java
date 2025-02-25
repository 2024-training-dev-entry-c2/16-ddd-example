package com.buildingblocks.combat.infra.mainservice.controllers;

import com.buildingblocks.shared.application.deckOfCards.reorganizeDeck.ReorganizeDeckRequest;
import com.buildingblocks.shared.application.deckOfCards.reorganizeDeck.ReorganizeDeckUseCase;
import com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/reorganize-deck")
public class ReorganizeDeckController {
    private final ReorganizeDeckUseCase useCase;

    public ReorganizeDeckController(ReorganizeDeckUseCase useCase) {
        this.useCase = useCase;
    }
    @PostMapping
    public Mono<DeckOfCardsResponse> reorganizeDeck(@RequestBody ReorganizeDeckRequest request){
        return useCase.execute(request);
    }
}
