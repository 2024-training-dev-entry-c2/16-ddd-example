package com.buildingblocks.shared.application.combat.domain.deckOfCards;

import com.buildingblocks.shared.application.combat.domain.deckOfCards.entities.SkillCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardAdded;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardDiscarded;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.CardRemoved;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.ImprovedCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.LostCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.RecoveredCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.ReorganizedDeck;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.events.RestedCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.EffectType;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.InitiativeCard;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.Objetives;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.Scope;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.SkillCardId;
import com.buildingblocks.shared.application.combat.domain.deckOfCards.values.SkillCardName;
import com.buildingblocks.shared.application.shared.domain.generic.DomainActionsContainer;
import com.buildingblocks.shared.application.shared.domain.generic.DomainEvent;

import java.util.Collections;
import java.util.function.Consumer;

public class DeckOfCardsHandler extends DomainActionsContainer {

    public DeckOfCardsHandler(DeckOfCards deck) {
        add(addCard(deck));
        add(removeCard(deck));
        add(reorganizeDeck(deck));
        add(restCard(deck));
        add(improveCard(deck));
        add(lostCard(deck));
        add(recoverCard(deck));
        add(discardCard(deck));
    }

    public Consumer<? extends DomainEvent> addCard(DeckOfCards deck) {
        return (CardAdded event) -> {
            System.out.println("agregando carta.......");

            deck.getSkillCards().removeIf(card ->
                    card.getIdentity().getValue() != null && card.getIdentity().equals(event.getCardId())
            );

            deck.getSkillCards().add(new SkillCard(
                    event.getCardId() == null || event.getCardId().isEmpty() ? null : SkillCardId.of(event.getCardId()),
                    SkillCardName.of(event.getCardName()),
                    InitiativeCard.of(event.getInitiative()),
                    EffectType.of(event.getEffectType(), event.getDuration(), event.getIntensity()),
                    Objetives.of(event.getObjectives(), false),
                    Scope.of(event.getScope())
            ));

            System.out.println(deck.getSkillCards().get(0).getIdentity().getValue());
        };
    }

    public Consumer<? extends DomainEvent> removeCard(DeckOfCards deck) {
        return (CardRemoved event) -> {
            int card = deck.findCardById(event.getCardId());
            deck.getSkillCards().remove(card);
        };
    }

    public Consumer<? extends DomainEvent> reorganizeDeck(DeckOfCards deck) {
        return (ReorganizedDeck event) -> {
            Collections.shuffle(deck.getSkillCards());

        };
    }

    public Consumer<? extends DomainEvent> restCard(DeckOfCards deck) {
        return (RestedCard event) -> {
            if (Boolean.TRUE.equals(event.isLongRest())) {
                deck.getSkillCards().addAll(deck.getDiscardedCards());
                deck.getDiscardedCards().clear();
            } else {
                if (!deck.getDiscardedCards().isEmpty()) {
                    SkillCard card = deck.getDiscardedCards().remove(0);
                    deck.getSkillCards().add(card);
                }
            }
        };
    }

    public Consumer<? extends DomainEvent> improveCard(DeckOfCards deck) {
        return (ImprovedCard event) -> {
            SkillCard card = deck.getSkillCards().stream()
                    .filter(c -> c.getIdentity().getValue().equals(event.getCardId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Carta no encontrada"));
            card.applyEffect(event.getType());

        };
    }

    public Consumer<? extends DomainEvent> discardCard(DeckOfCards deck) {
        return (CardDiscarded event) -> {
            int card = deck.findCardById(event.getCardId());
            deck.getDiscardedCards().add(deck.getSkillCards().get(card));
            deck.getSkillCards().remove(card);
        };
    }

    public Consumer<? extends DomainEvent> lostCard(DeckOfCards deck) {
        return (LostCard event) -> {
            int card = deck.findCardById(event.getCardId());
            deck.getLostCards().add(deck.getSkillCards().get(card));
            deck.getSkillCards().remove(card);
        };
    }

    public Consumer<? extends DomainEvent> recoverCard(DeckOfCards deck) {
        return (RecoveredCard event) -> {
            if (deck.getLostCards() !=null) {
                deck.getSkillCards().addAll(deck.getLostCards());
                deck.getLostCards().clear();
            }
        };
    }


}
