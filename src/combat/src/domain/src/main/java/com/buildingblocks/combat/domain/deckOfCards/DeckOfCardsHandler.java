package com.buildingblocks.combat.domain.deckOfCards;

import com.buildingblocks.combat.domain.character.values.DeckOfCardsId;
import com.buildingblocks.combat.domain.deckOfCards.entities.SkillCard;
import com.buildingblocks.combat.domain.deckOfCards.events.CardAdded;
import com.buildingblocks.combat.domain.deckOfCards.events.CardRemoved;
import com.buildingblocks.combat.domain.deckOfCards.events.ImprovedCard;
import com.buildingblocks.combat.domain.deckOfCards.events.RecoveredCard;
import com.buildingblocks.combat.domain.deckOfCards.events.ReorganizedDeck;
import com.buildingblocks.combat.domain.deckOfCards.events.RestedCard;
import com.buildingblocks.combat.domain.deckOfCards.values.EffectType;
import com.buildingblocks.combat.domain.deckOfCards.values.InitiativeCard;
import com.buildingblocks.combat.domain.deckOfCards.values.Objetives;
import com.buildingblocks.combat.domain.deckOfCards.values.ParticipantId;
import com.buildingblocks.combat.domain.deckOfCards.values.Scope;
import com.buildingblocks.combat.domain.deckOfCards.values.SkillCardId;
import com.buildingblocks.combat.domain.deckOfCards.values.SkillCardName;
import com.buildingblocks.combat.domain.deckOfCards.values.StatusDeck;
import com.buildingblocks.shared.domain.generic.AggregateRoot;
import com.buildingblocks.shared.domain.generic.DomainActionsContainer;
import com.buildingblocks.shared.domain.generic.DomainActionsHandler;
import com.buildingblocks.shared.domain.generic.DomainEvent;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class DeckOfCardsHandler extends DomainActionsContainer {

    public DeckOfCardsHandler(DeckOfCards deck) {
        add(addCard(deck));
        add(removeCard(deck));
        add(reorganizeDeck(deck));
        add(restCard(deck));
        add(improveCard(deck));
        add(discardCard(deck));
        add(lostCard(deck));
        add(recoverCard(deck));
    }

    public Consumer<? extends DomainEvent> addCard(DeckOfCards deck) {
        return (CardAdded event) -> {
            if (deck.getSkillCards() == null) {
                List<SkillCard> cards = new ArrayList<>();
                deck.setSkillCards(cards);
            }
            deck.getSkillCards().add(new SkillCard(
                    SkillCardId.of(event.getCardId()),
                    SkillCardName.of(event.getCardName()),
                    InitiativeCard.of(event.getInitiative()),
                    EffectType.of(event.getEffectType(), event.getDuration(), event.getIntensity()),
                    Objetives.of(event.getObjectives(), false),
                    Scope.of(event.getScope())
            ));
        };
    }

    public Consumer<? extends DomainEvent> removeCard(DeckOfCards deck) {
        return (CardRemoved event) -> {
            if (deck.getSkillCards() == null) {
                System.out.println("no hay nada que borrar");
            }
            deck.getSkillCards().remove(Integer.parseInt(event.getCardId()));
        };
    }

    public Consumer<? extends DomainEvent> reorganizeDeck(DeckOfCards deck) {
        return (ReorganizedDeck event) -> {
            Collections.shuffle(deck.getSkillCards());

        };
    }

    public Consumer<? extends DomainEvent> restCard(DeckOfCards deck) {
        return (RestedCard event) -> {
            Collections.shuffle(deck.getSkillCards());
            if (event.isLongRest()) {
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
        return (CardRemoved event) -> {
            SkillCard card = deck.findCardById(event.getCardId());
            deck.getDiscardedCards().add(card);
        };
    }

    public Consumer<? extends DomainEvent> lostCard(DeckOfCards deck) {
        return (CardRemoved event) -> {
            SkillCard card = deck.findCardById(event.getCardId());
            deck.getSkillCards().remove(card);
            deck.getLostCards().add(card);
        };
    }

    public Consumer<? extends DomainEvent> recoverCard(DeckOfCards deck) {
        return (RecoveredCard event) -> {
            SkillCard card = deck.findCardById(event.getCardId());
            if (deck.getDiscardedCards().contains(card)) {
                deck.getDiscardedCards().remove(card);
                deck.getSkillCards().add(card);

            } else {
                throw new IllegalArgumentException("La carta no está descartada.");
            }
        };
    }

}
