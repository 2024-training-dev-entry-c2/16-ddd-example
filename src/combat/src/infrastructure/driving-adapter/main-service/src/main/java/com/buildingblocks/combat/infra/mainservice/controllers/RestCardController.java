package com.buildingblocks.combat.infra.mainservice.controllers;

import com.buildingblocks.shared.application.deckOfCards.restCards.RestCardRequest;
import com.buildingblocks.shared.application.deckOfCards.restCards.RestCardUseCase;
import com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
    @RequestMapping("/api/rest-card")
public class RestCardController {
    private final RestCardUseCase restCardUseCase;

    public RestCardController(RestCardUseCase restCardUseCase) {
        this.restCardUseCase = restCardUseCase;
    }
    @PostMapping
    public Mono<DeckOfCardsResponse> restCard(@RequestBody RestCardRequest request){
       return restCardUseCase.execute(request);
    }

}
