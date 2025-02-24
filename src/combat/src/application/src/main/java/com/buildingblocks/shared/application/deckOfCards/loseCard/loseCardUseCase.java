package com.buildingblocks.shared.application.deckOfCards.loseCard;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.DeckOfCards;
import com.buildingblocks.shared.application.shared.IEventsRepository;
import com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsMapper.mapToResponse;

public class loseCardUseCase implements ICommandUseCase<loseCardRequest, Mono<DeckOfCardsResponse>> {
    private final IEventsRepository repository;

    public loseCardUseCase(IEventsRepository repository) {
        this.repository = repository;
    }
    @Override
    public Mono<DeckOfCardsResponse> execute(loseCardRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    DeckOfCards deck = DeckOfCards.from(request.getAggregateId(), events);
                    deck.loseCard(request.getCardId());
                    deck.getUncommittedEvents().forEach(repository::save);
                    deck.markEventAsCommited();
                    Map<String, Object> eventDetails = new HashMap<>();
                    eventDetails.put("cardId", request.getCardId());
                    return mapToResponse(deck, eventDetails);
                        }
                );
    }
}
