package com.buildingblocks.combat.infra.mainservice.controllers;

import com.buildingblocks.shared.application.deckOfCards.improveCard.ImproveCardRequest;
import com.buildingblocks.shared.application.deckOfCards.improveCard.ImproveCardUseCase;
import com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/improve-card")
public class ImproveCardController {
    private final ImproveCardUseCase improveCardUseCase;

    public ImproveCardController(ImproveCardUseCase improveCardUseCase) {
        this.improveCardUseCase = improveCardUseCase;
    }

    @PostMapping
    public Mono<DeckOfCardsResponse> improveCard(@RequestBody ImproveCardRequest request) {
        return improveCardUseCase.execute(request);
    }

}
