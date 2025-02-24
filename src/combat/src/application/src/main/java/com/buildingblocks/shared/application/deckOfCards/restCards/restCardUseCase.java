package com.buildingblocks.shared.application.deckOfCards.restCards;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.DeckOfCards;
import com.buildingblocks.shared.application.shared.IEventsRepository;
import com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsMapper.mapToResponse;

public class restCardUseCase implements ICommandUseCase<restCardRequest, Mono<DeckOfCardsResponse>> {
    private final IEventsRepository repository;

    public restCardUseCase(IEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<DeckOfCardsResponse> execute(restCardRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    DeckOfCards deck = DeckOfCards.from(request.getAggregateId(), events);
                    deck.restCards(request.getLongRest());
                    deck.getUncommittedEvents().forEach(repository::save);
                    deck.markEventAsCommited();
                    Map<String, Object> eventDetails = new HashMap<>();
                    eventDetails.put("action", "rest");
                    eventDetails.put("isLongRest", request.getLongRest());
                    return mapToResponse(deck, eventDetails);

                });
    }
}
