package com.buildingblocks.shared.application.deckOfCards.removeCard;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.DeckOfCards;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;
import com.buildingblocks.shared.application.shared.ports.IEventsRepositoryPort;
import com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsResponse;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsMapper.mapToResponse;

public class RemoveCardUseCase implements ICommandUseCase<RemoveCardRequest, Mono<DeckOfCardsResponse>> {

    private final IEventsRepositoryPort repository;

    public RemoveCardUseCase(IEventsRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<DeckOfCardsResponse> execute(RemoveCardRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                    events.sort(Comparator.comparing(DomainEvent::getWhen));

                    DeckOfCards deck = DeckOfCards.from(request.getAggregateId(), events);
                            deck.removeCard(request.getId());
                            deck.getUncommittedEvents().forEach(repository::save);
                            deck.markEventAsCommited();
                            Map<String, Object> eventDetails = new HashMap<>();
                            eventDetails.put("cardId", request.getId());
                            return mapToResponse(deck, eventDetails);
                        }

                );
    }
}
