package com.buildingblocks.combat.infra.mainservice.controllers;

import com.buildingblocks.shared.application.deckOfCards.addCard.AddCardRequest;
import com.buildingblocks.shared.application.deckOfCards.addCard.AddCardUseCase;
import com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/add-card")
public class AddCardController {

    private final AddCardUseCase addCardUseCase;

    public AddCardController(AddCardUseCase addCardUseCase) {
        this.addCardUseCase = addCardUseCase;
    }
    @PostMapping
    public Mono<DeckOfCardsResponse> addCard(@RequestBody AddCardRequest request){
        return addCardUseCase.execute(request);
    }
}
