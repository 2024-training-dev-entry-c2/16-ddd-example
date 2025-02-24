package com.buildingblocks.shared.application.deckOfCards.recoverCard;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.DeckOfCards;
import com.buildingblocks.shared.application.shared.IEventsRepository;
import com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsMapper.mapToResponse;

public class RecoverCardUseCase implements ICommandUseCase<RecoverCardRequest, Mono<DeckOfCardsResponse>> {
    private final IEventsRepository repository;

    public RecoverCardUseCase(IEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<DeckOfCardsResponse> execute(RecoverCardRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    DeckOfCards deck = DeckOfCards.from(request.getAggregateId(), events);
                    deck.recoverCard(request.getCardId());
                    deck.getUncommittedEvents().forEach(repository::save);
                    deck.markEventAsCommited();
                    Map<String, Object> eventDetails = new HashMap<>();
                    eventDetails.put("cardId", request.getCardId());
                    return mapToResponse(deck, eventDetails);
                });
    }
}
