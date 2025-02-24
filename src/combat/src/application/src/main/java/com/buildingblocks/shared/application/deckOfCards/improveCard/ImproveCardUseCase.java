package com.buildingblocks.shared.application.deckOfCards.improveCard;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.DeckOfCards;
import com.buildingblocks.shared.application.shared.IEventsRepository;
import com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsMapper.mapToResponse;

public class ImproveCardUseCase implements ICommandUseCase<ImproveCardRequest, Mono<DeckOfCardsResponse>> {
    private final IEventsRepository repository;

    public ImproveCardUseCase(IEventsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<DeckOfCardsResponse> execute(ImproveCardRequest request) {
        return repository.findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events -> {
                            DeckOfCards deck = DeckOfCards.from(request.getAggregateId(), events);
                            deck.improveCard(request.getCardId(), request.getImprovement());
                            deck.getUncommittedEvents().forEach(repository::save);
                            deck.markEventAsCommited();
                            Map<String, Object> eventDetails = new HashMap<>();
                            eventDetails.put("cardId", request.getCardId());
                            eventDetails.put("improvement", request.getImprovement());
                            return mapToResponse(deck, eventDetails);
                        }

                );
    }
}
