package com.buildingblocks.shared.application.deckOfCards.addCard;

import com.buildingblocks.shared.application.ICommandUseCase;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.DeckOfCards;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.entities.SkillCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.EffectType;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.InitiativeCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.Objetives;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.Scope;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.SkillCardId;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.SkillCardName;
import com.buildingblocks.shared.application.shared.ports.IEventsRepositoryPort;
import com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static com.buildingblocks.shared.application.shared.deckOfCards.DeckOfCardsMapper.mapToResponse;

public class AddCardUseCase implements ICommandUseCase<AddCardRequest, Mono<DeckOfCardsResponse>> {
    private final IEventsRepositoryPort repository;

    public AddCardUseCase(IEventsRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<DeckOfCardsResponse> execute(AddCardRequest request) {
        return repository
                .findEventsByAggregateId(request.getAggregateId())
                .collectList()
                .map(events->{
                    DeckOfCards deck =  DeckOfCards.from(request.getAggregateId(),events);
                    SkillCard card = new SkillCard(
                            SkillCardId.of(request.getCardId()),
                            SkillCardName.of(request.getSkillCardName()),
                            InitiativeCard.of(request.getInitiative()),
                            EffectType.of(request.getNameEffect(), request.getDuration(), request.getImpact()),
                            Objetives.of(request.getObjetives(), false),
                            Scope.of(request.getScope())
                    );
                    deck.addCard(card);
                    deck.getUncommittedEvents().forEach(repository::save);
                    deck.markEventAsCommited();
                    Map<String, Object> eventDetails = new HashMap<>();
                    eventDetails.put("cardId", card.getIdentity().getValue());
                    eventDetails.put("cardName", card.getSkillCardName().getValue());
                    eventDetails.put("initiative", card.getIniciative().getValue());
                    return mapToResponse(deck,eventDetails);
                });
    }
}
